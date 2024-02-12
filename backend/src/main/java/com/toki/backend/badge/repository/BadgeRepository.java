package com.toki.backend.badge.repository;

import com.toki.backend.badge.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BadgeRepository extends JpaRepository<Badge, Integer> {

    //1. 배지 정보 추가. 2. 전체 등록된 배지 정보 조회 3. 사용자 배지 등록(회원에게 배지 추가)

    Optional<Badge> findByIdx(int idx); //주어진 인덱스에 해당하는 배지를 찾아서 반환.

    List<Badge> findAll(); // 모든 배지 조회하여 리스트로 반환.

    // 특정 사용자가 가지고 있는 배지 조회
    List<Badge> findByUserTag(String userTag); //유저태그로 회원을 찾아서 그 회원의 배지정보를 리스트로 반환.


}
