package com.toki.backend.memberBadges.entitiy;

import com.toki.backend.badge.entity.Badge;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@EntityListeners(AuditingEntityListener.class)
public class MemberBadges {
    //pk(Long), badge_pk(int), user_pk(varchar(36)), create_at(datetime)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne

    private MemberBadges pk;

    @ManyToOne
    private Badge idx;

    @CreatedDate
    private LocalDateTime createAt; //생성일자 자동기입
}
