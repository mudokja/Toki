package com.toki.backend.roomchat.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomChatMessageDto {
    private String roomChatPk;
    private String fromUser;
    private String content;
}
