package com.toki.backend.member.repository;

import com.toki.backend.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * member 테이블 관련 Jpa 인터페이스
 */
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUserId(String user);
	Optional<User> findByUserTag(User user);

    Optional<User> findByUserIdAndSnsType(String userId, int snsType);

	Optional<Object> findByUserPk(String userPk);
}
