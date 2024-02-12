package com.toki.backend.member.repository;

import com.toki.backend.badge.entity.Badge;
import com.toki.backend.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * member 테이블 관련 Jpa 인터페이스
 */
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUserId(String user);
	Optional<User> findByUserTag(Integer userTag);

    Optional<User> findByUserIdAndSnsType(String userId, int snsType);

	Optional<User> findByUserPk(String userPk);




	// 최대 userTag 값을 가져오는 메서드 (추가)
	@Query("SELECT MAX(u.userTag) FROM User u")
	Integer findMaxUserTag();

	List<User> findByBadge(Badge badge);

	// 사용자가 특정 배지를 포함하는지 확인하기 위한 메서드 -> 추가하였음.
	List<User> findByBadgesContain(Badge badge);
}
