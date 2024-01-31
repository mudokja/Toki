package com.toki.backend.complaint.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "complaint")
@Builder
@AllArgsConstructor
public class ComplaintEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @Column(length = 36)
    private String fromUserPk;

    @Column(length = 36)
    private String toUserPk;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private LocalDateTime creatAt;

    @PrePersist
    protected void onCreate() { //creatAt에 자동으로 현재 시간이 등록
        creatAt = LocalDateTime.now();
    }
}
