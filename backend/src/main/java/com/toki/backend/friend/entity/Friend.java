package com.toki.backend.friend.entity;

import com.toki.backend.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    @ManyToOne
    private User fromUser;

    @Column(nullable = false)
    @ManyToOne
    private User toUser;

    @Column(nullable = false)
    private Boolean isFriend;

    @CreatedDate
    private LocalDateTime createAt;

}
