package com.toki.backend.blacklist.repository;

import com.toki.backend.member.entity.User;
import com.toki.backend.blacklist.entity.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Integer> {


    List<Blacklist> findAllByFromUserPk(User fromUserId);

    Blacklist findByFromUserPkAndToUserPk(User fromUserPk, User toUserPk);


}
