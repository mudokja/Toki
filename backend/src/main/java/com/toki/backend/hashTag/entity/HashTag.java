package com.toki.backend.hashTag.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hashTag") //데이터베이스에서 사용될 테이블 이름은 "hashTag"로 지정
@Builder
public class HashTag {

    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idx; //해시태그 인덱스

    @Column(length = 50)
    private String tagName; //해시태그의 이름

    @CreatedDate
    private LocalDateTime createdAt; // 해시태그가 생성된 일자를 저장

    @Column
    private long score; //해시태그의 사용횟수를 저장

    @CreatedDate
    private LocalDateTime lastUsedAt; //해시태그가 마지막으로 사용된 일자를 저장
}
