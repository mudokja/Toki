package com.toki.backend.roomchat.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toki.backend.roomchat.dto.request.RoomChatRequestMessageDto;
import com.toki.backend.roomchat.service.RoomChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RoomChatMessageController {

    private final SimpMessageSendingOperations sendingOperations;
    private final RoomChatService roomChatService;
    private final RedisTemplate<String, String> redisTemplate;
    @MessageMapping("/room/enter")
    public void enter(@Payload RoomChatRequestMessageDto roomChatRequestDto, SimpMessageHeaderAccessor headerAccessor) {



    }
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        log.debug("Received a new web socket connection");
    }


    @MessageMapping("/room/{chatRoomPk}")
//    @SendTo("/subChat/room/{chatRoomPk}")
    public void sendMessage(
            @DestinationVariable String chatRoomPk, @Payload RoomChatRequestMessageDto roomChatRequestDto
//            , @AuthenticationPrincipal CustomOAuth2User principal
            ) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        if(!chatRoomPk.equals(roomChatRequestDto.getRoomChatPk())){
            roomChatRequestDto.setRoomChatPk(chatRoomPk);
        }
        // 때를기다리며
//        String userPk=principal.getName();
        log.debug("장소 {}",chatRoomPk);
        log.debug("메시지 {}",roomChatRequestDto.toString());
//        redisTemplate.convertAndSend("roomChat",objectMapper.writeValueAsString(roomChatRequestDto));
        roomChatService.sendMessage(roomChatRequestDto,"tempPk",roomChatRequestDto.getFromUserNickName());
//                , principal.getName()
//        );

    }


    @EventListener
    public void disconnectListener(SessionDisconnectEvent event) {
        log.debug("연결 끊김 이벤트 message {}, user {}",event.getMessage(), Objects.requireNonNull(event.getUser()).getName());
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());

        log.info("headAccessor {}", accessor);
        String roomChatPk = (String) accessor.getSessionAttributes().get("roomChatPk");

            sendingOperations.convertAndSend("/subChat/room/" + roomChatPk, "aa");
        }
    }



