package com.toki.backend.roomchat.dto.response;

import com.toki.backend.roomchat.dto.RoomChatDto;
import com.toki.backend.roomchat.dto.RoomChatType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RoomChatResponseDto extends RoomChatDto {
    @Builder
    public RoomChatResponseDto(String roomChatPk, String fromUser, String content) {
        super(roomChatPk, fromUser, content);
    }
}
