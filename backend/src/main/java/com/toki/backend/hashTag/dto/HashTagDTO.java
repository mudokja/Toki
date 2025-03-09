package com.toki.backend.hashTag.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HashTagDTO {

    private int idx; //해시태그의 인덱스
    private String tagName; //해시태그의 이름
    private LocalDateTime createdAt; //해시태그 생성일자
    private long score; //해시태그 사용횟수 
    private LocalDateTime lastUsedAt; //해시태그 마지막 사용일자

    // 생성자, Getter, Setter, toString 등이 자동으로 생성
}
