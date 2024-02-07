package com.toki.backend.roomchat.dto.response;

import com.toki.backend.roomchat.dto.RoomChatMessageDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RoomChatResponseMessageDto extends RoomChatMessageDto {
    @Builder
    public RoomChatResponseMessageDto(String roomChatPk, String fromUser, String content) {
        super(roomChatPk, fromUser, content);
    }
}
