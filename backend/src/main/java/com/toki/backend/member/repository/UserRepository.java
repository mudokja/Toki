package com.toki.backend.member.repository;

import com.toki.backend.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * member 테이블 관련 Jpa 인터페이스
 */
public interface UserRepository extends JpaRepository<User, String> {


//	// userPk로 사용자를 찾는 메서드 -> 필요없어서 주석처리하였습니다.
//	Optional<User> findByUserPk(String userPk);


	// 사용자 태그로 사용자를 찾는 메서드
	Optional<User> findByUserTag(String userTag);


	// 사용자 ID와 SNS 타입으로 사용자를 찾는 메서드
    Optional<User> findByUserIdAndSnsType(String userPk, int snsType);
//	List<User> findByBadges(Badge badge);	//Badge를 기준으로 사용자를 검색


	// 최대 userTag 값을 가져오는 메서드 (추가)
	@Query("SELECT MAX(u.userTag) FROM User u")
	Integer findMaxUserTag();
}
