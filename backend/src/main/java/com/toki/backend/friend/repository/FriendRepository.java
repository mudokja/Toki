package com.toki.backend.friend.repository;

import com.toki.backend.member.entity.User;
import com.toki.backend.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findAllByFromUserAndIsFriend(User fromUser, Boolean isFriend);

    List<Friend> findAllByToUserAndIsFriend(User toUser, Boolean isFriend);

    Friend findByFromUserAndToUser(User fromUser, User toUser);

}
