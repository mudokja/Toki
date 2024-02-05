package com.toki.backend.badge.entity;


import com.toki.backend.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

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
    private int idx;

    @Column(length = 100)
    private String name;

    @Column(length = 512)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


}
