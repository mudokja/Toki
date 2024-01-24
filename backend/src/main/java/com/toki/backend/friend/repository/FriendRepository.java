package com.toki.backend.friend.repository;

import com.toki.backend.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("select f from Friend f where f.fromUserId = :fromUserId and f.isFriend = :isFriend")
    List<Friend> findAllByFromUserIdAndIsFriend(String fromUserId, Boolean isFriend);

    @Query("select f from Friend f where f.toUserId = :toUserId and f.isFriend = :isFriend")
    List<Friend> findAllByToUserIdAndIsFriend(String toUserId, Boolean isFriend);

    @Query("select f from Friend f where f.fromUserId = :fromUserId and f.toUserId = :toUserId")
    Friend findByFromUserIdAndToUserId(String fromUserId, String toUserId);
}
