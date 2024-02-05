package com.toki.backend.room.dto.response;

import lombok.Builder;

@Builder
public class RoomListByCategoryDto {
    int categoryPk;
    String categoryName;
    Object rooms;
}
