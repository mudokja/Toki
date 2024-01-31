package com.toki.backend.complaint.service;

import com.toki.backend.complaint.dto.ComplaintDTO;
import com.toki.backend.complaint.entity.ComplaintEntity;
import com.toki.backend.complaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;


    // 다른 유저를 신고하는 기능
    public ComplaintDTO reportUser(String fromUserPk, String toUserPk, String description) {
        LocalDateTime createdAt = LocalDateTime.now();

        ComplaintEntity complaintEntity = ComplaintEntity.builder()
                .fromUserPk(fromUserPk)
                .toUserPk(toUserPk)
                .description(description)
                .createdAt(createdAt)
                .build();

        ComplaintEntity savedComplaint = complaintRepository.save(complaintEntity);

        return convertEntityToDto(savedComplaint);
    }

    // Entity를 DTO로 변환
    private ComplaintDTO convertEntityToDto(ComplaintEntity complaintEntity) {
        return ComplaintDTO.builder()
                .idx(complaintEntity.getIdx())
                .fromUserPk(complaintEntity.getFromUserPk())
                .toUserPk(complaintEntity.getToUserPk())
                .description(complaintEntity.getDescription())
                .createdAt(complaintEntity.getCreatedAt())
                .build();
    }


}
