package com.toki.backend.room.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Set;

@Getter
@AllArgsConstructor
@Builder
@RedisHash(value = "room_member")
public class RoomMember {
    @Id
    private String roomPk;

    @Setter
    private Set<String> members;

}
