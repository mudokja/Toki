package com.toki.backend.friend.repository;

import com.toki.backend.member.entity.User;
import com.toki.backend.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("select new com.toki.backend.friend.dto.response.FriendResponseDto(f.toUser.userNickName, f.toUser.userTag, f.toUser.profileImageUrl) " +
            "from Friend f " +
            "where f.fromUser=:fromUser and f.isFriend=:isFriend")
    List<Friend> findAllByFromUserAndIsFriend(User fromUser, Boolean isFriend);

    @Query("select new com.toki.backend.friend.dto.response.FriendResponseDto(f.toUser.userNickName, f.toUser.userTag, f.toUser.profileImageUrl) " +
            "from Friend f " +
            "where f.toUser=:toUser and f.isFriend=:isFriend")
    List<Friend> findAllByToUserAndIsFriend(User toUser, Boolean isFriend);

    Friend findByFromUserAndToUser(User fromUser, User toUser);

}
