package com.toki.backend.blacklist.dto.request;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BlacklistSaveRequestDto {
    private String toUserPk;

    @Builder
    public BlacklistSaveRequestDto(String toUserPk) {
        this.toUserPk = toUserPk;
    }
}
