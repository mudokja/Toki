package com.toki.backend.room.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "room_hashtag")
public class RoomTag {
    @Id
    private Long idx;
    @Indexed
    private String roomPk;

    @Indexed
    private String tags;
}