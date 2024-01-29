package com.toki.backend.friend.repository;

import com.toki.backend.auth.entity.User;
import com.toki.backend.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findAllByFromUserAndIsFriend(User fromUser, Boolean isFriend);

    List<Friend> findAllByToUserAndIsFriend(User toUser, Boolean isFriend);

    Friend findByFromUserAndToUser(User fromUser, User toUser);

}
