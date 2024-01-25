package com.toki.backend.blacklist.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlacklistRequestDto {

    private String toUserId;

    private String fromUserId;
}
