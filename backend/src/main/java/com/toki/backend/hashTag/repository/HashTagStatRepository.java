package com.toki.backend.hashTag.repository;

import com.toki.backend.hashTag.entity.HashTagStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface HashTagStatRepository extends JpaRepository<HashTagStat, Integer> {


    Optional<HashTagStat> findByTagName(String tagName);

    // 태그 사용 횟수 기준으로 상위 5개 조회
    List<HashTagStat> findTop5();
}
