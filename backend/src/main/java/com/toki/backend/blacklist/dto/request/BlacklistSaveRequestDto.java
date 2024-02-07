package com.toki.backend.blacklist.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BlacklistSaveRequestDto {
    private String toUserTag;

    @Builder
    public BlacklistSaveRequestDto(String toUserTag) {
        this.toUserTag = toUserTag;
    }
}
