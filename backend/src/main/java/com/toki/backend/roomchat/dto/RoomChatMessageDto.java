package com.toki.backend.roomchat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomChatMessageDto {
    private String roomChatPk;
    private String fromUser;
    private String content;
}
