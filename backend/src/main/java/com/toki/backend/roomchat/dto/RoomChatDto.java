package com.toki.backend.roomchat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomChatDto {
    private String roomChatPk;
    private String fromUser;
    private String content;
}
