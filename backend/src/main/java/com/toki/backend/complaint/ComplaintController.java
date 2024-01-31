package com.toki.backend.complaint;

import com.toki.backend.complaint.dto.ComplaintDTO;
import com.toki.backend.complaint.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/complaints")
@RequiredArgsConstructor
@CrossOrigin
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping
    public ResponseEntity<ComplaintDTO> reportUser(@RequestParam String fromUserPk,
                                                   @RequestParam String toUserPk,
                                                   @RequestParam String description) {
        ComplaintDTO newComplaint = complaintService.reportUser(fromUserPk, toUserPk, description);
        System.out.println("DATA FROM FE = " + fromUserPk+" " + toUserPk+" " + description +  " received");
        return new ResponseEntity<>(newComplaint, HttpStatus.CREATED);
    }
}
