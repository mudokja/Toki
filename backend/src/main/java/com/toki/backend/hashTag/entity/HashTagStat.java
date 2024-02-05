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
@Table(name = "hash_tag_stat")
@Builder
public class HashTagStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idx;

    @Column(length = 50)
    private String tagName;

    @Column
    private LocalDateTime createAt;

    @Column
    private int score;


}
