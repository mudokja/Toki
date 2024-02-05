package com.toki.backend.room.dto.request;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
public class CreateRoomRequestDto {

    String roomName;
    Integer categoryPk;
    List<String> tags;
    Boolean isPrivate;
    String roomPassword;
    String parentRoomId;
    Object roomOption;

}