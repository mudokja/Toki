package com.toki.backend.hashTag.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;




@Entity // JPA 엔티티임을 나타내는 어노테이션
@Getter // Lombok을 사용하여 자동으로 게터 생성
@Setter // Lombok을 사용하여 자동으로 세터 생성
@ToString // Lombok을 사용하여 자동으로 toString 메서드 생성
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 사용하는 생성자 생성
@Table(name = "hash_tag_stat") // 데이터베이스 테이블과 매핑되는 이름 설정
@Builder // 빌더 패턴을 사용할 수 있도록 설정
public class HashTagStat {

    @Id // 해당 필드를 엔티티의 기본 키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임하는 전략 설정
    @Column // 엔티티의 컬럼임을 나타내는 어노테이션
    private int idx; //인덱스

    @Column(length = 50) //컬럼의 길이(50) 설정
    private String tagName; //태그 이름

    @Column //컬럼
    private LocalDateTime createAt; //생성일자

    @Column //컬럼
    private int score; //해시태그 사용 횟수 기록을 위한 필드명


}
