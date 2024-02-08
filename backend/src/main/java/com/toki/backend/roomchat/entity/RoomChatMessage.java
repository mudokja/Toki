package com.toki.backend.roomchat.entity;


import com.toki.backend.roomchat.dto.RoomChatType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;


@EntityListeners(value = AuditingEntityListener.class)
@RedisHash(value = "roomchatmessage")
@Getter
@NoArgsConstructor
@ToString
public class RoomChatMessage {
    @Id
    private String roomChatPk;

    @Indexed
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomChatIdx;

    private RoomChatType chatType;

    @Column
    private String fromUser;
    @Column
    private String sendTo;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime crateAt;

    @Builder
    public RoomChatMessage(String roomChatPk, Integer roomChatIdx, RoomChatType chatType, String fromUser, String sendTo, LocalDateTime crateAt) {
        this.roomChatPk = roomChatPk;
        this.roomChatIdx = roomChatIdx;
        this.chatType = chatType;
        this.fromUser = fromUser;
        this.sendTo = sendTo;
        this.crateAt = crateAt;
    }

}
