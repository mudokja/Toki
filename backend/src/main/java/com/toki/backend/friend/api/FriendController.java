package com.toki.backend.friend.api;


import com.toki.backend.auth.entity.User;
import com.toki.backend.auth.repository.UserRepository;
import com.toki.backend.auth.service.CustomOAuth2User;
import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.friend.dto.FriendDto;
import com.toki.backend.friend.dto.request.FriendRequestDto;
import com.toki.backend.friend.dto.request.FriendRequestProcessDto;
import com.toki.backend.friend.entity.Friend;
import com.toki.backend.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    private final UserRepository userRepository;

    // 친구 목록 조회 | 받은 친구 요청 조회
    @GetMapping
    public ResponseEntity<CommonResponseDto<Object>> findFriends(@AuthenticationPrincipal CustomOAuth2User userPrincipal,
                                                    @RequestParam Boolean isFriend) {
        List<Friend> data;
        User MyUser = userRepository.findById(userPrincipal.getName()).get();

        if (isFriend) {
            data = friendService.getFriendListByFromUserAndIsFriend(MyUser);
        }
        else {
            data = friendService.getFriendListByToUserAndNotIsFriend(MyUser);
        }

        CommonResponseDto<Object> responseDto = CommonResponseDto.builder()
                .resultCode(200)//success
                .resultMessage("조회에 성공했습니다.")
                .data(data)
                .build();
        return ResponseEntity.ok(responseDto);
    }


    // 친구 요청
    @PostMapping
    public ResponseEntity<CommonResponseDto<Object>> requestFriend(@RequestBody FriendRequestDto requestDto,
                                           @AuthenticationPrincipal CustomOAuth2User userPrincipal) {

        User myUser = userRepository.findById(userPrincipal.getName()).get();
        User toUser = userRepository.findByUserTag(requestDto.getToUserTag()).get();

        friendService.saveFriendByNotIsFriend(FriendDto.builder()
                .fromUser(myUser)
                .toUser(toUser)
                .build()
        );

        CommonResponseDto<Object> responseDto = CommonResponseDto.builder()
                .resultCode(200)
                .resultMessage("친구 요청에 성공했습니다.")
                .build();
        return ResponseEntity.ok(responseDto);
    }


    // 친구 요청 수락 또는 거절
    @PutMapping
    public ResponseEntity<CommonResponseDto<Object>> requestFriendProcess(@RequestBody FriendRequestProcessDto requestDto,
                                                                          @AuthenticationPrincipal CustomOAuth2User userPrincipal) {

        User myUser = userRepository.findById(userPrincipal.getName()).get();
        User toUser = userRepository.findByUserTag(requestDto.getToUserTag()).get();

        if (requestDto.getAcceptFriend()) {

            friendService.updateFriendByIsFriend(FriendDto.builder()
                    .fromUser(toUser)
                    .toUser(myUser)
                    .build()
            );

            friendService.saveFriendByIsFriend(FriendDto.builder()
                    .fromUser(myUser)
                    .toUser(toUser)
                    .build()
            );


            CommonResponseDto<Object> responseDto = CommonResponseDto.builder()
                    .resultCode(200)
                    .resultMessage("친구 수락에 성공했습니다.")
                    .build();
            return ResponseEntity.ok(responseDto);


        } else {
            friendService.deleteFriend(FriendDto.builder()
                    .fromUser(toUser)
                    .toUser(myUser)
                    .build()
            );


            CommonResponseDto<Object> responseDto = CommonResponseDto.builder()
                    .resultCode(200)
                    .resultMessage("친구 거절에 성공했습니다.")
                    .build();
            return ResponseEntity.ok(responseDto);
        }



    }

    // 친구 삭제
    @DeleteMapping("/{toUserTag}")
    public ResponseEntity<?> deleteFriend(@PathVariable String toUserTag,
                                          @AuthenticationPrincipal CustomOAuth2User userPrincipal) {

        User myUser = userRepository.findById(userPrincipal.getName()).get();
        User toUser = userRepository.findByUserTag(toUserTag).get();

        friendService.deleteFriend(FriendDto.builder()
                .fromUser(toUser)
                .toUser(myUser)
                .build()
        );
        friendService.deleteFriend(FriendDto.builder()
                .fromUser(myUser)
                .toUser(toUser)
                .build()
        );

        CommonResponseDto<Object> responseDto = CommonResponseDto.builder()
                .resultCode(200)
                .resultMessage("친구 삭제에 성공했습니다.")
                .build();
        return ResponseEntity.ok(responseDto);

    }
}
