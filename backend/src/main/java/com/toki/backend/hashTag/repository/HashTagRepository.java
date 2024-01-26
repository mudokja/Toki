package com.toki.backend.hashTag.repository;

import com.toki.backend.hashTag.entity.HashTag;
import net.minidev.json.JSONUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, Integer> {
    //태그 찾기, 태그 등록, 태그 삭제, 태그 수정
    Optional<HashTag> findByTagName(String tagName);

    List<HashTag> findAll();

    Optional<HashTag> findByIdx(int idx);


    //태그 등록 , 삭제, 수정은 (CRUD)  Spring Data JPA에서는 JpaRepository를 상속받은 인터페이스를 사용하면,
    // 기본적인 CRUD 기능이 자동으로 제공된다.



}