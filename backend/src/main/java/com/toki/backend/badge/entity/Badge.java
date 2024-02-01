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
    private int idx;

    @Column(length = 100)
    private String name;

    @Column(length = 512)
    private String imageUrl;

    @OneToMany(mappedBy = "badge")
    private List<MemberBadge> memberBadges = new ArrayList<>();
}
