package com.toki.backend.room.api;


import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.room.entity.Room;
import com.toki.backend.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    // 화상 채팅방 페이지 별 목록 간단 조회
    @GetMapping
    public ResponseEntity<> findRoomListByPage(@RequestParam(required = false, defaultValue = "1") int pageNumber) {

        List<Room> rooms = roomService.getRoomListByPage(pageNumber);

        roomName:string,
                category: string,
                tags: Array<string>,
        isPrivate:boolean,
        currentCount:number,
                roomOption: Object

        CommonResponseDto responseDto = CommonResponseDto.builder()
                .resultCode(200)
                .resultMessage(pageNumber + "페이지 조회에 성공했습니다.")
                .data()

    }
}
