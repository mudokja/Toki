package com.toki.backend.blacklist.dto.request;


import lombok.*;

@Getter
@NoArgsConstructor
public class BlacklistRequestDto {

    private String toUserPk;

    private String fromUserPk;

    @Builder
    public BlacklistRequestDto(String toUserPk, String fromUserPk) {
        this.toUserPk = toUserPk;
        this.fromUserPk = fromUserPk;
    }
}
