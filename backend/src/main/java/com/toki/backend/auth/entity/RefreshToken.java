package com.toki.backend.auth.entity;

import com.toki.backend.auth.dto.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

/**
 * 리프레시 토큰 엔티티
 */

@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@RedisHash("refresh_token")
@ToString
public class RefreshToken {
    @Id
    String refreshToken;
    @CreatedDate
    LocalDateTime createAt;
    LocalDateTime expireTime;
    Integer snsType;
    @Enumerated(EnumType.STRING)
    Role role;
    String userPk;
    String userTag;

    @Builder
    public RefreshToken(String refreshToken, LocalDateTime expireTime,String userTag, Integer snsType, Role role, String userPk) {
        this.refreshToken = refreshToken;
        this.expireTime = expireTime;
        this.snsType = snsType;
        this.role = role;
        this.userPk = userPk;
        this.userTag= userTag;
    }
}
