package com.toki.backend.roomchat.entity;


import com.toki.backend.roomchat.dto.RoomChatType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
@RedisHash(value = "room_chat")
public class RoomChat {
    @Id
    @NotNull
    private String RoomChatPk;

    @CreatedDate
    private LocalDateTime createAt;

    private RoomChatType chatType;

    @Column
    private String fromUserPk;
    @Column
    private String sendTo;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime crateAt;


}
