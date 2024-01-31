package com.toki.backend.friend.service;

import com.toki.backend.auth.entity.User;
import com.toki.backend.auth.repository.UserRepository;
import com.toki.backend.auth.service.CustomOAuth2User;
import com.toki.backend.friend.dto.request.CommonFriendDto;
import com.toki.backend.friend.dto.request.FriendRequestDto;
import com.toki.backend.friend.dto.request.FriendRequestProcessDto;
import com.toki.backend.friend.entity.Friend;
import com.toki.backend.friend.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public List<Friend> getFriendListByFromUserAndIsFriend(FriendRequestDto requestDto) {

        User fromUser = User.builder()
                .userPk(requestDto.getFromUserPk())
                .build();

        return friendRepository.findAllByFromUserPkAndIsFriend(fromUser, true);
    }

    public List<Friend> getFriendListByToUserAndNotIsFriend(FriendRequestDto requestDto) {

        User toUser = User.builder()
                .userPk(requestDto.getToUserPk())
                .build();

        return friendRepository.findAllByToUserPkAndIsFriend(toUser, false);
    }


    public void saveFriendByNotIsFriend(FriendRequestDto requestDto) {

        User fromUserPk = User.builder()
                        .userPk(requestDto.getFromUserPk())
                        .build();
        User toUserPk = User.builder()
                        .userPk(requestDto.getToUserPk())
                        .build();


        friendRepository.save(
                Friend.builder()
                        .fromUserPk(fromUserPk)
                        .toUserPk(toUserPk)
                        .isFriend(false)
                        .build()
        );
    }

    public void updateFriendByIsFriend(CommonFriendDto requestDto) {

        User fromUserPk = User.builder()
                .userPk(requestDto.getFromUserPk())
                .build();
        User toUserPk = User.builder()
                .userPk(requestDto.getToUserPk())
                .build();

        Friend friend = friendRepository.findByFromUserPkAndToUserPk(fromUserPk, toUserPk);

        friend.setIsFriend(true);
    }


    public void saveFriendByIsFriend(CommonFriendDto requestDto) {

        User fromUserPk = User.builder()
                .userPk(requestDto.getFromUserPk())
                .build();
        User toUserPk = User.builder()
                .userPk(requestDto.getToUserPk())
                .build();

        friendRepository.save(
                Friend.builder()
                        .fromUserPk(fromUserPk)
                        .toUserPk(toUserPk)
                        .isFriend(true)
                        .build()
        );
    }

    public void deleteFriend(CommonFriendDto requestDto) {

        User fromUserPk = User.builder()
                .userPk(requestDto.getFromUserPk())
                .build();
        User toUserPk = User.builder()
                .userPk(requestDto.getToUserPk())
                .build();

        Friend friend = friendRepository.findByFromUserPkAndToUserPk(fromUserPk, toUserPk);

        friendRepository.delete(friend);

    }

}
