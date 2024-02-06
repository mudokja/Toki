package com.toki.backend.friend.repository;

import com.toki.backend.member.entity.User;
import com.toki.backend.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findAllByFromUserPkAndIsFriend(User fromUserPk, Boolean isFriend);

    List<Friend> findAllByToUserPkAndIsFriend(User toUserPk, Boolean isFriend);

    Friend findByFromUserPkAndToUserPk(User fromUserPk, User toUserPk);

}
