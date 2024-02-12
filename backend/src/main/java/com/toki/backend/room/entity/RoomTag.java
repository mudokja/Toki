package com.toki.backend.room.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "room_hashtag")
public class RoomTag {
    @Id
    private Integer roomPk;

    @Setter
    private Set<String> tags;
}