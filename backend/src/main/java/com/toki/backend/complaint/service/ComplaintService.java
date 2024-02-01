package com.toki.backend.complaint.service;

import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.complaint.dto.ComplaintDTO;
import com.toki.backend.complaint.entity.ComplaintEntity;
import com.toki.backend.complaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;


@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;


    // 다른 유저를 신고하는 기능
    public CommonResponseDto<ComplaintDTO> reportUser(String fromUserPk, String toUserPk, String description) {
        try {
            // 현재 시각을 생성 시각으로 사용
            LocalDateTime creatAt = LocalDateTime.now();

            // ComplaintDTO 객체를 생성하여 저장
            ComplaintDTO complaintDTO = ComplaintDTO.builder()
                    .fromUserPk(fromUserPk)
                    .toUserPk(toUserPk)
                    .description(description)
                    .creatAt(creatAt)
                    .build();

            // DTO를 엔티티로 변환하여 저장
            ComplaintEntity complaintEntity = convertDtoToEntity(complaintDTO);
            ComplaintEntity savedComplaint = complaintRepository.save(complaintEntity);

            // 저장된 ComplaintEntity를 DTO로 변환하여 응답
            return CommonResponseDto.<ComplaintDTO>builder()
                    .resultCode(200)
                    .resultMessage("신고가 성공적으로 접수되었습니다.")
                    .data(convertEntityToDto(savedComplaint))
                    .build();
        } catch (Exception e) {
            // 예외 처리 또는 로깅 추가
            return CommonResponseDto.<ComplaintDTO>builder()
                    .resultCode(400)
                    .resultMessage("신고를 접수하는 중에 오류가 발생하였습니다.")
                    .build();
        }
    }

    // DTO를 엔티티로 변환하는 메서드
    private ComplaintEntity convertDtoToEntity(ComplaintDTO complaintDTO) {
        return ComplaintEntity.builder()
                .fromUserPk(complaintDTO.getFromUserPk())
                .toUserPk(complaintDTO.getToUserPk())
                .description(complaintDTO.getDescription())
                .creatAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
                .build();
    }

    // Entity를 DTO로 변환
    private ComplaintDTO convertEntityToDto(ComplaintEntity complaint) {
        return ComplaintDTO.builder()
                .idx(complaint.getIdx())
                .fromUserPk(complaint.getFromUserPk())
                .toUserPk(complaint.getToUserPk())
                .description(complaint.getDescription())
                .creatAt(complaint.getCreatAt())
                .build();
    }



}
