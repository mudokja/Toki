package com.toki.backend.banner.repository;

import com.toki.backend.auth.entity.Banner;

import com.toki.backend.auth.entity.RefreshToekn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BannerRepository extends JpaRepository<Banner, String> {



    //배너 전체 조회
    List<Banner> findAllBanners();

    //특정 인덱스를 가진 배너를 조회한다.
    Optional<Banner> findByIdx(int idx);





}
