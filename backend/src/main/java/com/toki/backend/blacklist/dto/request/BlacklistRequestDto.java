package com.toki.backend.blacklist.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlacklistRequestDto {

    private String toUserPk;

    private String fromUserPk;
}
