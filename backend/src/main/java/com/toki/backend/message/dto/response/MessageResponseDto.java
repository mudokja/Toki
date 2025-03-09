package com.toki.backend.message.dto.response;

import com.toki.backend.common.utils.ConvertUserTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class MessageResponseDto {
    private Long messagePk;
    private String fromUserNickname;
    private String fromUserTag;
    private String fromUserProfileImageUrl;
    private String content;

    public MessageResponseDto(Long messagePk, String fromUserNickname, Integer fromUserTag, String fromUserProfileImageUrl, String content) {
        this.messagePk = messagePk;
        this.fromUserNickname = fromUserNickname;
        this.fromUserTag = ConvertUserTag.convertUserTag(fromUserTag);
        this.fromUserProfileImageUrl = fromUserProfileImageUrl;
        this.content = content;
    }
}

