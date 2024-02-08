package com.toki.backend.roomchat.entity;


import com.toki.backend.roomchat.dto.RoomChatType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
@RedisHash(value = "room_chat")
@Getter
@NoArgsConstructor
@ToString
public class RoomChatMessage {
    @Id
    private String RoomChatPk;

    @Indexed
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer RoomChatIdx;

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
        RoomChatPk = roomChatPk;
        RoomChatIdx = roomChatIdx;
        this.chatType = chatType;
        this.fromUser = fromUser;
        this.sendTo = sendTo;
        this.crateAt = crateAt;
    }

}
