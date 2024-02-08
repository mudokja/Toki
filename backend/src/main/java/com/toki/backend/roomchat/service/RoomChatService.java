package com.toki.backend.roomchat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toki.backend.member.repository.UserRepository;
import com.toki.backend.roomchat.dto.RoomChatType;
import com.toki.backend.roomchat.dto.request.RoomChatRequestMessageDto;
import com.toki.backend.roomchat.dto.response.RoomChatResponseMessageDto;
import com.toki.backend.roomchat.entity.RoomChatMessage;
import com.toki.backend.roomchat.repository.RoomChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoomChatService {
    private final RoomChatMessageRepository roomChatMessageRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;
    private final ChannelTopic channelTopic;
    private final ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    public void sendMessage(RoomChatRequestMessageDto roomChatRequestDto, String userPk) throws JsonProcessingException {



        //채팅 생성 및 저장
        RoomChatMessage roomChatMessage = RoomChatMessage.builder()
                .roomChatPk(roomChatRequestDto.getRoomChatPk())
                .chatType(roomChatRequestDto.getChatType())
                .fromUser(userPk)
                .sendTo(roomChatRequestDto.getSendTo())
                        .build();
        log.debug("채팅 엔티티 {}",roomChatMessage.toString());
        roomChatMessageRepository.save(roomChatMessage);
        String topic = channelTopic.getTopic();
        RoomChatResponseMessageDto roomChatResponseMessageDto = RoomChatResponseMessageDto.builder()
                .roomChatPk(roomChatRequestDto.getRoomChatPk())
                .fromUser(userPk)
                .content(roomChatRequestDto.getContent())
                .build();
        log.debug("요청 주소 {}, 개체 {}",topic,roomChatResponseMessageDto);
        if (roomChatRequestDto.getChatType() == RoomChatType.COMMONCHAT) {
            // 그륩 채팅일 경우
            sendCommonChat(topic,roomChatResponseMessageDto);
        }
    }
    public void sendCommonChat(String topic, RoomChatResponseMessageDto roomChatResponseMessageDto) throws JsonProcessingException {
        redisTemplate.convertAndSend(topic, objectMapper.writeValueAsString(roomChatResponseMessageDto));
    }
    public void deleteAndUpdateRoomChatLog(String roomChatPk){
            List<RoomChatMessage> roomChatList=roomChatMessageRepository.findAllByRoomChatPk(roomChatPk);
            log.debug(roomChatList.toString());


    }
}
