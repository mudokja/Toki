package com.toki.backend.roomchat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomChatMessageDto {
    private String roomChatPk;
    @JsonProperty("fromUser")
    private String fromUserNickName;
    private String content;
}
