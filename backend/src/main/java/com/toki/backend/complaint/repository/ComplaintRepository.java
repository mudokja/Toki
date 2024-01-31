package com.toki.backend.complaint.repository;

import com.toki.backend.complaint.entity.ComplaintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ComplaintRepository extends JpaRepository<ComplaintEntity, Long>{

    //첫 번째 매개변수는 엔티티 타입이며, 두 번째 매개변수는 엔티티의 기본 키 타입입니다.
    // 여기서 ComplaintEntity는 엔티티이고, Long은 ComplaintEntity의 기본 키인 idx의 타입입니다.
}
