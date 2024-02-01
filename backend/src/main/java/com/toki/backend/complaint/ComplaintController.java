package com.toki.backend.complaint;

import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.complaint.dto.ComplaintDTO;
import com.toki.backend.complaint.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor
@CrossOrigin
public class ComplaintController {

    private final ComplaintService complaintService;

    // 다른 유저를 신고
    @PostMapping
    public CommonResponseDto<ComplaintDTO> reportUser(@RequestBody ComplaintDTO complaintDTO) {
        // ComplaintService를 통해 다른 유저를 신고하고 결과를 반환
        return complaintService.reportUser(
                complaintDTO.getFromUserPk(),
                complaintDTO.getToUserPk(),
                complaintDTO.getDescription()
        );
    }
}