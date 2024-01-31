package com.toki.backend.badge.entity;


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


}
