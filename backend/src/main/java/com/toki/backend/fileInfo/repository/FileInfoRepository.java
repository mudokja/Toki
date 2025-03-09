package com.toki.backend.fileInfo.repository;

import com.toki.backend.fileInfo.entity.FileInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfoEntity, Long> {

    //따로 등록할 것은 없을 것이라고 생각 ->CRUD만 필요??

    // 따로 추가적인 메서드를 선언하지 않아도 Spring Data JPA에서 제공하는 기능으로 CRUD 가능



}
