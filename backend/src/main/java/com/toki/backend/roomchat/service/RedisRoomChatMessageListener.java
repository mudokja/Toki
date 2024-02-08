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

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisRoomChatMessageListener implements MessageListener {
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, String> redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.debug("뭔가왔다아아!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        log.info("Message= {}", message);
        try {
            String publishMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());

            RoomChatResponseMessageDto responseMessageDto = objectMapper.readValue(publishMessage, RoomChatResponseMessageDto.class);
            log.debug("보내진 메시지 {}",publishMessage);

                messagingTemplate.convertAndSend("/subChat/room/" + responseMessageDto.getRoomChatPk(), Objects.requireNonNull(publishMessage));


        } catch (Exception e) {
            log.debug("채팅 오류 {}",e.getMessage());
        }
    }
}
