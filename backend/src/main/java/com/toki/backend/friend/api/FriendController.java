package com.toki.backend.friend.api;


import com.toki.backend.auth.entity.User;
import com.toki.backend.friend.dto.FriendRequest;
import com.toki.backend.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 내 친구 전체 목록 보기
    @GetMapping("/api/v1/friends")
    public ResponseEntity<List<User>> findFriendListByMemberId(@RequestBody FriendRequest friendRequest) {
        return friendService.findFriendListByMemberId(
    }


    // 친구 추가
    @PostMapping("/api/v1/friends")

    // 친구 확인
    @PutMapping("/api/v1/friends")

    // 친구 삭제 또는 취소 처리
    @DeleteMapping("/api/v1/friends")


}
