package com.toki.backend.room.entity;


import com.toki.backend.auth.entity.User;
import com.toki.backend.hashTag.entity.HashTag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomPk;

    @ManyToOne
    private Room parentRoomPk;

    @Column(nullable = false)
    private String title;

    @OneToOne
    private Category categoryPk;

    @OneToMany
    private List<User> members = new ArrayList<>();

    @Column(nullable = false)
    private Boolean isPrivate;

    @Column
    private String password;

    @Column
    private String sessionId;

    // 해시태그는 어떤 방식으로 짜여졌는지를 봐야 할 듯
    @JdbcTypeCode(SqlTypes.JSON)
    private HashTag hashTag;


}
