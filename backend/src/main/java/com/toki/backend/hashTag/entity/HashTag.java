package com.toki.backend.hashTag.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hashTag")
@Builder
public class HashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idx;

    @Column(length = 50)
    private String tagName;

    @Column
    private LocalDateTime createdAt;

    @Column
    private long score;

    @Column
    private LocalDateTime lastUsedAt;
}
