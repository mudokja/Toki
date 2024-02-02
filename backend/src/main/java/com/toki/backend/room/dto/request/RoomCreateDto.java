package com.toki.backend.room.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class RoomCreateDto {

    String roomName;
    Integer categoryPk;
    List<String> tags;
    Boolean isPrivate;
    String roomPassword;

}