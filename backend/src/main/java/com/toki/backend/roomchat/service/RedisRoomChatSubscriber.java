package com.toki.backend.roomchat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toki.backend.roomchat.dto.RoomChatType;
import com.toki.backend.roomchat.dto.request.RoomChatRequestMessageDto;
import com.toki.backend.roomchat.dto.response.RoomChatResponseMessageDto;
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

            RoomChatRequestMessageDto roomChatRequestDto = objectMapper.readValue(publishMessage, RoomChatRequestMessageDto.class);
            log.debug(publishMessage);

            if (roomChatRequestDto.getRoomChatType().equals(RoomChatType.COMMONCHAT)) {
                RoomChatResponseMessageDto roomChatResponseDto = RoomChatResponseMessageDto.builder()
                                .roomChatPk(roomChatRequestDto.getRoomChatPk())
                        .content(roomChatRequestDto.getContent())
                                        .build();
                messagingTemplate.convertAndSend("/subChat/room/" + roomChatRequestDto.getRoomChatPk(), roomChatResponseDto);
            }


        } catch (Exception e) {
            log.debug("채팅 오류 {}",e.getMessage());
        }
    }
}
