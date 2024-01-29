package com.toki.backend.friend.api;


import com.toki.backend.auth.dto.snsUser.OAuth2UserInfo;
import com.toki.backend.auth.entity.User;
import com.toki.backend.auth.service.CustomOAuth2User;
import com.toki.backend.friend.dto.FriendRequestDto;
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

    // 친구 목록 조회
    @GetMapping("")
    public ResponseEntity<List<Friend>> findFriendList(@AuthenticationPrincipal CustomOAuth2User userPrincipal) {

        String userPk = userPrincipal.getName();
        List<Friend> friendList= friendService.getFriendList(userPk);
        return ResponseEntity.ok().body(friendList);
    }


    // 친구 요청
    @PostMapping
    public ResponseEntity<?> requestFriend(@RequestBody FriendRequestDto requestDto,
                                           @AuthenticationPrincipal CustomOAuth2User userPrincipal) {

        String userPk = userPrincipal.getName();
        requestDto.setFromUserId(userPk);

        try {
            friendService.addFriend(requestDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().build();
    }

    // 친구 요청 수락 또는 거절
    @PostMapping("/{friendPk}")
    public ResponseEntity<?> requestFriendProcess(@PathVariable String friendPk,
                                                  @RequestBody FriendRequestDto requestDto) {

        try {
            friendService.acceptFriend(friendRequestDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().build();

    }

    // 친구 삭제
    @DeleteMapping("/{friendPk}")
    public ResponseEntity<?> deleteFriend(@PathVariable String friendPk) {

        // 토큰에서 유저의 pk 값을 받는다
        String userPk = "ABC"; // 고쳐야함

        // 넣어준다.
        return ResponseEntity.ok().build();

    }
}
