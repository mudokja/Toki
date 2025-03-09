package com.toki.backend.banner.repository;

import com.toki.backend.badge.entity.Badge;
import com.toki.backend.banner.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {


    //1. 배너 전체 조회  2. 특정 배너 조회, 3. 삭제 4. 추가. 5.수정
    //배너 전체 조회
    List<Banner> findAll();

    //특정 인덱스를 가진 배너를 조회한다.
    Optional<Banner> findByIdx(int idx);






}
