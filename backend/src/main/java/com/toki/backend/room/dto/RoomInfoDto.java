package com.toki.backend.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomInfoDto {
    String roomName;
    Integer categoryPk;
    String categoryName;
    Boolean isPrivate;
    Integer currentCount;
    List<String> tags;
}
