package com.toki.backend.roomchat.api;

import com.toki.backend.auth.service.CustomOAuth2User;
import com.toki.backend.roomchat.dto.request.RoomChatRequestMessageDto;
import com.toki.backend.roomchat.service.RoomChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RoomChatController {

    private final SimpMessageSendingOperations sendingOperations;
    private final RoomChatService roomChatService;

    @MessageMapping("/room/enter")
    public void enter(@Payload RoomChatRequestMessageDto roomChatRequestDto, SimpMessageHeaderAccessor headerAccessor) {



    }


    @MessageMapping("/room/{chatRoomPk}")
    public void sendMessage(@DestinationVariable String chatRoomPk, @Payload RoomChatRequestMessageDto roomChatRequestDto, @AuthenticationPrincipal CustomOAuth2User principal) {

        roomChatService.sendMessage(roomChatRequestDto, principal.getName());

    }


    @EventListener()
    public void disconnectListener(SessionDisconnectEvent event,StompHeaderAccessor headerAccessor) {


        log.info("headAccessor {}", headerAccessor);
        String roomChatPk = (String) headerAccessor.getSessionAttributes().get("roomChatPk");

            sendingOperations.convertAndSend("/subChat/room/" + roomChatPk, "aa");
        }
    }



