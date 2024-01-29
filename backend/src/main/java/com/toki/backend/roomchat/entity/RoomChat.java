package com.toki.backend.roomchat.entity;

import com.toki.backend.auth.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = AuditingEntityListener.class)
@Builder
@RedisHash(value = "room_chat")
public class RoomChat {

    @Id
    private Long idx;

//    @Column
//    @ManyToOne
//    private Room roomPk;

    @CreatedDate
    private LocalDateTime createAt;

    @Column
    @ManyToOne
    private User fromUserPk;

    @Column
    @ManyToOne
    private User toUserPk;

    @Column
    private String context;

    @TimeToLive
    private Long expiredTime;

}
