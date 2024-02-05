package com.toki.backend.member.repository;

import com.toki.backend.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByUserId(String user);
    Optional<Member> findByUserTag(Member user);

    Optional<Member> findByUserIdAndSnsType(String userId, int snsType);

    Optional<Object> findByUserPk(String userPk);
}
