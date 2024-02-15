package com.toki.backend.room.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CreateRoomRequestDto {

    String roomName;
    Integer categoryPk;
    List<String> tags;
    Boolean isPrivate;
    String roomPassword;
    String parentRoomId;
    Object roomOption;

}