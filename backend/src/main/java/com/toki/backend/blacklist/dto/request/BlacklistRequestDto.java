package com.toki.backend.blacklist.dto.request;


import lombok.*;

@Getter
@NoArgsConstructor
public class BlacklistRequestDto {

    private String toUserTag;

    private String fromUserPk;

    @Builder
    public BlacklistRequestDto(String toUserTag, String fromUserPk) {
        this.toUserTag = toUserTag;
        this.fromUserPk = fromUserPk;
    }
}
