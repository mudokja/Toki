package com.toki.backend.hashTag.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HashTagDTO {

    private int idx;
    private String tagName;
    private LocalDateTime createdAt;
    private long score;
    private LocalDateTime lastUsedAt;

    // 생성자, Getter, Setter, toString 등이 자동으로 생성 (베리 굿...)
}
