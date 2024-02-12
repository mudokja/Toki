package com.toki.backend.room.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CreateRoomResponseDto {
    private Integer roomId;
    private String roomName;
    private String sessionId;
    private Set<String> hashTag;
}
