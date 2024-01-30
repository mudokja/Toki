package com.toki.backend.friend.service;

import com.toki.backend.auth.entity.User;
import com.toki.backend.auth.repository.UserRepository;
import com.toki.backend.friend.dto.FriendRequestDto;
import com.toki.backend.friend.dto.FriendResponseDto;
import com.toki.backend.friend.entity.Friend;
import com.toki.backend.friend.repository.FriendRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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

        return friendRepository.findAllByFromUserAndIsFriend(fromUser, true);
    }

    public List<Friend> getFriendListByToUserAndNotIsFriend(FriendRequestDto requestDto) {

        User toUser = User.builder()
                .userPk(requestDto.getToUserPk())
                .build();

        return friendRepository.findAllByToUserAndIsFriend(toUser, false);
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


    // 친구 수락
    @Transactional
    public void acceptFriend(FriendRequestDto requestDto) throws Exception {

        // fromUserId, toUserId 로 검색한 뒤에 isFriend 1로 수정
        // 둘이 바꾸고 isFriend 가 1인 값으로 새로 생성

        // 수정 작업
        Friend friend = friendRepository.findByFromUserAndToUser(requestDto.getFromUser(), requestDto.getToUser())
        friend.setIsFriend(true);

        // 생성 작업
        friendRepository.save(
                Friend.builder()
                        .fromUserId(requestDto.getToUser())
                        .toUserId(requestDto.getFromUser())
                        .isFriend(true)
                        .build()
        );
    }


    // 친구 거절
    @Transactional
    public void rejectFriend(FriendRequestDto requestDto) {

        // fromUserId, toUserId 로 검색한 뒤에 데이터 삭제
        // 성공했다고 반환

        // 삭제 작업
        Friend friend = friendRepository.findByFromUserAndToUser(requestDto.getFromUser(), requestDto.getToUser());
        friendRepository.delete(friend);

    }

    // 친구 삭제
    @Transactional
    public void deleteFriend(FriendRequestDto requestDto) {

        // fromUserId, toUserId 로 검색한 뒤에 데이터 삭제
        // Id 값을 바꾼 값도 데이터 삭제

        // 삭제 작업
        Friend fromFriend = friendRepository.findByFromUserAndToUser(requestDto.getFromUser(), requestDto.getToUser());
        friendRepository.delete(fromFriend);

        //친구도 삭제
        Friend toFriend = friendRepository.findByFromUserAndToUser(requestDto.getToUser(), requestDto.getFromUser());
        friendRepository.delete(toFriend);

    }

}
