package com.toki.backend.room.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JoinRoomRequestDto {

    String sessionId;
    String roomPassword;
}
