package com.toki.backend.friend.repository;

import com.toki.backend.auth.entity.User;
import com.toki.backend.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findAllByFromUserPkAndIsFriend(User fromUserPk, Boolean isFriend);

    List<Friend> findAllByToUserPkAndIsFriend(User toUserPk, Boolean isFriend);

    Friend findByFromUserPkAndToUserPk(User fromUserPk, User toUserPk);

}
