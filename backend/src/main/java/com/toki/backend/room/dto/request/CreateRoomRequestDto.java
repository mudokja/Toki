package com.toki.backend.room.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
public class CreateRoomRequestDto {

    String roomName;
    Integer categoryPk;
    Set<String> tags;
    Boolean isPrivate;
    String roomPassword;
    String parentRoomId;
    Object roomOption;

}