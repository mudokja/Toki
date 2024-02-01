package com.toki.backend.blacklist.dto.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlacklistSaveRequestDto {
    private String toUserPk;
}
