package com.toki.backend.auth.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "banner")
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int idx;

    @Column(length = 100)
    String name;

    @Column(length = 512)
    String imageUrl;

    @Builder
    public Banner(int idx, String name, String imageUrl) {
        this.idx = idx;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
