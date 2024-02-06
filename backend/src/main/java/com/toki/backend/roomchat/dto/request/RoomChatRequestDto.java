package com.toki.backend.roomchat.dto.request;

import com.toki.backend.roomchat.dto.RoomChatDto;
import com.toki.backend.roomchat.dto.RoomChatType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RoomChatRequestDto extends RoomChatDto {

    RoomChatType roomChatType;
    @Builder
    public RoomChatRequestDto(String roomChatPk, String fromUser, String content, RoomChatType roomChatType) {
        super(roomChatPk, fromUser, content);
        this.roomChatType = roomChatType;
    }
}
