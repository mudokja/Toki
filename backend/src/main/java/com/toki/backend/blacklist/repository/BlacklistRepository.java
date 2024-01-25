package com.toki.backend.blacklist.repository;

import com.toki.backend.blacklist.entity.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Integer> {

    @Query("select b from Blacklist b where b.fromUserId = :fromUserId")
    List<Blacklist> findAllByFromUserPk(String fromUserId);

    @Query("select b from Blacklist b where b.fromUserId = :fromUserId and b.toUserId = :toUserId")
    Blacklist findByFromUserIdAndToUserId(String fromUserId, String toUserId);


}
