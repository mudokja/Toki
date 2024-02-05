package com.toki.backend.room.api;


import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.room.dto.request.CreateRoomRequestDto;
import com.toki.backend.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/roomsrrrrrrr")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    // 화상 채팅방 페이지 별 목록 간단 조회
    @GetMapping
    public ResponseEntity<CommonResponseDto<Object>> findRoomListByPage(@RequestParam(required = false, defaultValue = "0") int page ) {

        return ResponseEntity.ok().body(roomService.getRoomList(page));
    }

    // 검색어가 포함된 해시태그를 가지고 있는 화상 채팅방 조회
//    @GetMapping()
//    public ResponseEntity<CommonResponseDto<Object>> findRoomListByHashTag(@RequestParam String tagName) {
//
//        return ResponseEntity.ok().body(roomService.getRoomListByHashTag(tagName));
//    }

    // 카테고리 기반 화상 채팅방 조회
    @GetMapping("/search")
    public ResponseEntity<CommonResponseDto<Object>> findRoomListByCategory(@RequestParam int category,
                                                                            @RequestParam int page) {
        return ResponseEntity.ok().body(roomService.getRoomListByCategoryPk(category, page));
    }

    // 화상 채팅방 생성
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomRequestDto requestDto) {
        roomService.saveRoom(requestDto);
    }

    // 타 이용자가 화상 채팅방 접속

    // 화상 채팅방 정보 수정

    // 화상 채팅방 나가기

    // 화상 채팅방 태그 등록, 수정 및 삭제

    //
}
