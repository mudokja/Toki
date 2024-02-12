package com.toki.backend.roomchat.api;

import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.roomchat.dto.response.RoomChatResponseLogMessageDto;
import com.toki.backend.roomchat.entity.RoomChatMessage;
import com.toki.backend.roomchat.service.RoomChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chats")
public class RoomChatController {
    private final RoomChatService roomChatService;
    @GetMapping("")
    public ResponseEntity<CommonResponseDto<Object>> getRoomChatMessageLog(@RequestParam(name = "roompk") String roomPk){
        return ResponseEntity.ok(CommonResponseDto.builder()
                .data(RoomChatMessage.toResponseLogMessageDto(roomChatService.getLastRoomChatMessage(roomPk)))
                .resultCode(200)
                .resultMessage("채팅로그 응답")
                .build());
    }
}
