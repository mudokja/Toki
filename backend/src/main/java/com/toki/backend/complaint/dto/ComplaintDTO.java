package com.toki.backend.complaint.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintDTO {

    private Long idx;
    private String fromUserPk;
    private String toUserPk;
    private String description;
    private LocalDateTime createdAt;

}
