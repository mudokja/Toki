package com.toki.backend.roomchat.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
@Builder
@Getter
@Table(name = "room_caht_log")
public class RoomChatLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomChatLogIdx;

    @Column
    private String roomChatPk;
    private String roomTitle;

    private Integer roomChatSessionNumber;

    @Column(length = 16777215)
    private String roomChatLogContent;

    private LocalDateTime createAt;


}
