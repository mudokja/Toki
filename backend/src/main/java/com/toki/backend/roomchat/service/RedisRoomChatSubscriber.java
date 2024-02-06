package com.toki.backend.roomchat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toki.backend.roomchat.dto.RoomChatType;
import com.toki.backend.roomchat.dto.request.RoomChatRequestDto;
import com.toki.backend.roomchat.dto.response.RoomChatResponseDto;
import com.toki.backend.roomchat.entity.RoomChat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisRoomChatSubscriber implements MessageListener {
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, String> redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {

        log.info("Message= {}", message);
        try {
            String publishMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());

            RoomChatRequestDto roomChatRequestDto = objectMapper.readValue(publishMessage, RoomChatRequestDto.class);

            if (roomChatRequestDto.getRoomChatType().equals(RoomChatType.COMMONCHAT)) {
                RoomChatResponseDto roomChatResponseDto = RoomChatResponseDto.builder()
                                .roomChatPk(roomChatRequestDto.getRoomChatPk())
                                        .build();
                messagingTemplate.convertAndSend("/subChat/room/" + roomChatRequestDto.getRoomChatPk(), roomChatResponseDto);
            }


        } catch (Exception e) {
            log.debug("채팅 오류 {}",e.getMessage());
        }
    }
}
