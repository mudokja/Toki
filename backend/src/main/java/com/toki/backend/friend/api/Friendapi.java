package com.toki.backend.friend.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // 내 친구 전체 목록 보기
    @GetMapping("/api/v1/friends")
    public


    // 친구 추가
    @PostMapping("/api/v1/friends")

    // 친구 확인
    @PutMapping("/api/v1/friends")

    // 친구 삭제 또는 취소 처리
    @DeleteMapping("/api/v1/friends")


}
