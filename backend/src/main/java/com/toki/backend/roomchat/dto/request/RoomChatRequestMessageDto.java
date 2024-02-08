package com.toki.backend.roomchat.dto.request;

import com.toki.backend.roomchat.dto.RoomChatMessageDto;
import com.toki.backend.roomchat.dto.RoomChatType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RoomChatRequestMessageDto extends RoomChatMessageDto {

    RoomChatType roomChatType;
    String sendTO="ALL";
    @Builder
    public RoomChatRequestMessageDto(String roomChatPk, String fromUser, String content, RoomChatType roomChatType) {
        super(roomChatPk, fromUser, content);
        this.roomChatType = roomChatType;
    }
}
