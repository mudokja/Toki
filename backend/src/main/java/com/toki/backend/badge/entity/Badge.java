package com.toki.backend.badge.entity;


import com.toki.backend.member.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity //JPA 엔티티임을 나타내는 어노테이션
@Getter // Lombok을 사용하여 자동으로 게터, 세터 및 toString 메서드를 생성
@Setter // Lombok을 사용하여 자동으로 게터, 세터 및 toString 메서드를 생성
@ToString // Lombok을 사용하여 자동으로 게터, 세터 및 toString 메서드를 생성
@NoArgsConstructor //파라미터가 없는 기본 생성자와 모든 필드를 사용하는 생성자를 생성
@AllArgsConstructor
@Table(name = "badge")
@Builder
public class Badge {

    @Id //해당 필드를 엔티티의 기본 키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idx; //배지 인덱스

    @Column(length = 100)
    private String name; //배지 이름

    @Column(length = 512)
    private String imageUrl; //배지 이미지




//
    @ManyToMany(mappedBy = "badges") //123
    // 이렇게 하면 Badge 엔티티와 User 엔티티 간의 다대다 관계가 정의
    private List<User> users = new ArrayList<>();
    ///엔티티가 가진 사용자 목록

//    배지 엔티티는 배지의 정보를 저장하는데 사용되며,
//    사용자와의 다대다 관계를 설정하여 한 사용자가 여러 배지를 가질 수 있고,
//    한 배지는 여러 사용자에 의해 소유될 수 있습니다.


}
