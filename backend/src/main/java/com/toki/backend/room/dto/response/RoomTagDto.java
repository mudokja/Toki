package com.toki.backend.room.dto.response;

import com.toki.backend.room.entity.RoomTag;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;


@Getter
@ToString
public class RoomTagDto  {
    String roomPk;
    HashSet<String> tags;

    @Builder
    public RoomTagDto(String roomPk, HashSet<String> tags) {
    }
}
