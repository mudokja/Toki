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
import

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@TypeDefs

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

    @Column
    private Boolean isPrivate;

    @Column
    private String password;

    @Column
    private String sessionId;

    @JdbcTypeCode(SqlTypes.JSON)
    private HashTag tag;


}
