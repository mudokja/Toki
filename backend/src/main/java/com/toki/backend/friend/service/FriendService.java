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

    // 친구 목록 조회
    public List<Friend> findFriendList(String fromUserId) {

        return friendRepository.findAllByFromUserIdAndIsFriend(fromUserId, true);
    }


    // 나에게 온 친구 요청 조회
    public List<Friend> findFriendRequest(FriendRequestDto friendRequestDto) {

        // toUserId 로 검색
        // 검색 결과 중에 isFriend 가 0인 결과는 친구 요청이 온 것
        return friendRepository.findAllByToUserIdAndIsFriend(friendRequestDto.getToUserId(), false);
    }


    // 친구 추가
    public void addFriend(FriendRequestDto friendRequestDto) throws Exception {

        Boolean isFriend = false;

        // 토큰에서 fromUserPk를 찾는다.
        User fromUser = userRepository.findByUserId(friendRequestDto.getFromUserId()).orElseThrow(Exception::new);
        User toUser = userRepository.findByUserId(friendRequestDto.getToUserId()).orElseThrow(Exception::new);

        friendRepository.save(
                Friend.builder()
                        .fromUserId(fromUser.getUserPk())
                        .toUserId(toUser.getUserPk())
                        .isFriend(isFriend)
                        .build()
        );
    }


    // 친구 수락
    @Transactional
    public void acceptFriend(FriendRequestDto friendRequestDto) throws Exception {

        // fromUserId, toUserId 로 검색한 뒤에 isFriend 1로 수정
        // 둘이 바꾸고 isFriend 가 1인 값으로 새로 생성

        User fromUser = userRepository.findByUserId(friendRequestDto.getFromUserId()).orElseThrow(Exception::new);
        User toUser = userRepository.findByUserId(friendRequestDto.getToUserId()).orElseThrow(Exception::new);

        String fromUserId = fromUser.getUserPk();
        String toUserId = toUser.getUserPk();

        // 수정 작업
        Friend friend = friendRepository.findByFromUserIdAndToUserId(fromUserId, toUserId);
        friend.setIsFriend(true);

        // 생성 작업
        friendRepository.save(
                Friend.builder()
                        .fromUserId(toUserId)
                        .toUserId(fromUserId)
                        .isFriend(true)
                        .build()
        );
    }


    // 친구 거절
    @Transactional
    public void rejectFriend(FriendRequestDto friendRequestDto) throws Exception{

        // fromUserId, toUserId 로 검색한 뒤에 데이터 삭제
        // 성공했다고 반환

        User fromUser = userRepository.findByUserId(friendRequestDto.getFromUserId()).orElseThrow(Exception::new);
        User toUser = userRepository.findByUserId(friendRequestDto.getToUserId()).orElseThrow(Exception::new);

        String fromUserId = fromUser.getUserPk();
        String toUserId = toUser.getUserPk();

        // 삭제 작업
        Friend friend = friendRepository.findByFromUserIdAndToUserId(fromUserId, toUserId);
        friendRepository.delete(friend);

    }

    // 친구 삭제
    @Transactional
    public void deleteFriend(FriendRequestDto friendRequestDto) throws Exception {

        // fromUserId, toUserId 로 검색한 뒤에 데이터 삭제
        // Id 값을 바꾼 값도 데이터 삭제
        User fromUser = userRepository.findByUserId(friendRequestDto.getFromUserId()).orElseThrow(Exception::new);
        User toUser = userRepository.findByUserId(friendRequestDto.getToUserId()).orElseThrow(Exception::new);

        String fromUserId = fromUser.getUserPk();
        String toUserId = toUser.getUserPk();

        // 삭제 작업
        Friend fromFriend = friendRepository.findByFromUserIdAndToUserId(fromUserId, toUserId);
        friendRepository.delete(fromFriend);

        //친구도 삭제
        Friend toFriend = friendRepository.findByFromUserIdAndToUserId(toUserId, fromUserId);
        friendRepository.delete(toFriend);

    }

}
