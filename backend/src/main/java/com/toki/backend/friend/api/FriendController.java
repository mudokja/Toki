package com.toki.backend.friend.api;


import com.toki.backend.auth.entity.User;
import com.toki.backend.friend.dto.FriendRequestDto;
import com.toki.backend.friend.entity.Friend;
import com.toki.backend.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 친구 목록 조회
    @GetMapping("/")
    public ResponseEntity<List<Friend>> findFriendListByMemberId(FriendRequestDto friendRequestDto) {
        Friend friend = Friend.builder()
                .isFriend(true)
                .fromUserId("A")
                .toUserId("B")
                .build();
        return ResponseEntity.ok().body(List.of(friend));
//        return ResponseEntity.ok().body(friendService.findFriendList(friendRequest));
    }


    // 친구 추가
    @PostMapping
    public ResponseEntity<?> findFriendListByMemerId(@RequestBody FriendRequestDto friendRequest) {
        return ResponseEntity.ok().build();
//    }
//
//    // 친구 확인
//    @PutMapping
//
//    // 친구 삭제 또는 취소 처리
//    @DeleteMapping("{}")
//
//
    }
}
