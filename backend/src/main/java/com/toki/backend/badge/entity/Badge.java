package com.toki.backend.badge.entity;


import com.toki.backend.member.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "badge")
@Builder
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idx; //배지 인덱스

    @Column(length = 100)
    private String name; //배지 이름

    @Column(length = 512)
    private String imageUrl; //배지 이미지
    
    @Column
    private String userTag; //유저태그

//    @OneToMany(mappedBy = "badge")
//    private List<MemberBadge> memberBadges;
}
