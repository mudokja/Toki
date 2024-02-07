package com.toki.backend.message.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class MessageByToUserDto {
    private Long messagePk;
    private Integer toUserTag;
    private String content;
    private Boolean isRead;
    private LocalDateTime createAt;

}

