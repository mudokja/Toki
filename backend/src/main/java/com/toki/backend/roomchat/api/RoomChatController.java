package com.toki.backend.roomchat.api;

import com.toki.backend.roomchat.dto.request.RoomChatRequestDto;
import com.toki.backend.roomchat.entity.RoomChat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RoomChatController {

    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/room/enter")
    public void enter(@Payload RoomChatRequestDto roomChatRequestDto, SimpMessageHeaderAccessor headerAccessor) {



    }


    @MessageMapping("/chat/sendMessage")
    public void sendMessage(@Payload RoomChatRequestDto roomChatRequestDto) {
        sendingOperations.convertAndSend("/subChat/room");

    }


//    @EventListener(value = )
    public void webSocketDisconnectListener(SessionDisconnectEvent event,StompHeaderAccessor headerAccessor) {


        log.info("headAccessor {}", headerAccessor);
        String roomChatPk = (String) headerAccessor.getSessionAttributes().get("roomChatPk");

            sendingOperations.convertAndSend("/subChat/room/" + roomChatPk, "aa");
        }
    }



