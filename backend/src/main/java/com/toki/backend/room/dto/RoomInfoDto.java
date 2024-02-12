package com.toki.backend.room.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
public class RoomInfoDto {
    String roomName;
    Integer categoryPk;
    String categoryName;
    Boolean isPrivate;
}
