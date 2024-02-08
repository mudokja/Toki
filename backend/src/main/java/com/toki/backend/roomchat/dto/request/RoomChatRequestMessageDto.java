package com.toki.backend.roomchat.dto.request;

import com.toki.backend.roomchat.dto.RoomChatMessageDto;
import com.toki.backend.roomchat.dto.RoomChatType;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomChatRequestMessageDto extends RoomChatMessageDto {
    RoomChatType chatType;
    String sendTo;
    @Builder
    public RoomChatRequestMessageDto(String roomChatPk, String sendTo, String fromUser, String content, RoomChatType chatType) {
        super(roomChatPk, fromUser, content);
        this.chatType = chatType;
        this.sendTo=sendTo;
    }

    public RoomChatRequestMessageDto(String roomChatPk, String fromUser, String content, RoomChatType chatType, String sendTo) {
        super(roomChatPk, fromUser, content);
        this.chatType = chatType;
        this.sendTo = sendTo;
    }

    @Override
    public String toString() {
        return "RoomChatRequestMessageDto{" +
                "roomChatType=" + chatType +
                ", sendTo='" + sendTo + '\'' +
                "} " + super.toString();
    }
}
