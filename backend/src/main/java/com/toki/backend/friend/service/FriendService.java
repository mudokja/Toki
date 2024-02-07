package com.toki.backend.friend.service;

import com.toki.backend.member.entity.User;
import com.toki.backend.member.repository.UserRepository;
import com.toki.backend.friend.dto.FriendDto;
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

    public List<Friend> getFriendListByFromUserAndIsFriend(User fromUser) {
        return friendRepository.findAllByFromUserAndIsFriend(fromUser, true);
    }

    public List<Friend> getFriendListByToUserAndNotIsFriend(User toUser) {
        return friendRepository.findAllByToUserAndIsFriend(toUser, false);
    }


    public void saveFriendByNotIsFriend(FriendDto friendDto) {

        friendRepository.save(
                Friend.builder()
                        .fromUser(friendDto.getFromUser())
                        .toUser(friendDto.getToUser())
                        .isFriend(false)
                        .build()
        );
    }


    public void updateFriendByIsFriend(FriendDto friendDto) {
        Friend friend = friendRepository.findByFromUserAndToUser(friendDto.getFromUser(), friendDto.getToUser());
        friend.setIsFriend(true);
    }


    public void saveFriendByIsFriend(FriendDto friendDto) {

        friendRepository.save(
                Friend.builder()
                        .fromUser(friendDto.getFromUser())
                        .toUser(friendDto.getToUser())
                        .isFriend(true)
                        .build()
        );
    }


    public void deleteFriend(FriendDto friendDto) {
        Friend friend = friendRepository.findByFromUserAndToUser(friendDto.getFromUser(), friendDto.getToUser());
        friendRepository.delete(friend);
    }

}
