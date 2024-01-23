package com.toki.backend.friend.repository;

import com.toki.backend.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findAllByFromId(String fromId);
}
