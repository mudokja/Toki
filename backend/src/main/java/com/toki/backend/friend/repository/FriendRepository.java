package com.toki.backend.friend.repository;

import com.toki.backend.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findAllByFromUserIdAndIsFriend(String fromUserId, Boolean isFriend);

    List<Friend> findAllByToUserIdAndIsFriend(String toUserId, Boolean isFriend);

    Friend findByFromUserIdAndToUserId(String fromUserId, String toUserId);

}
