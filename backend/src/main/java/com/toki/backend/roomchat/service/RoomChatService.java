package com.toki.backend.roomchat.service;

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

    @Transactional
    public void sendMessage(RoomChatRequestMessageDto roomChatRequestDto, String userPk) {


        //채팅 생성 및 저장
        RoomChatMessage roomChatMessage = RoomChatMessage.builder()
                .roomChatPk(roomChatRequestDto.getRoomChatPk())
                .chatType(roomChatRequestDto.getRoomChatType())
                .fromUser(userPk)
                .sendTo(roomChatRequestDto.getSendTO())
                        .build();
        roomChatMessageRepository.save(roomChatMessage);
        String topic = channelTopic.getTopic();
        RoomChatResponseMessageDto roomChatResponseMssageDto = RoomChatResponseMessageDto.builder()
                .fromUser(userPk)
                .content(roomChatRequestDto.getContent())
                .build();

        if (roomChatRequestDto.getRoomChatType() == RoomChatType.COMMONCHAT) {
            // 그륩 채팅일 경우
            redisTemplate.convertAndSend(topic, roomChatResponseMssageDto);
        }
    }
    public void deleteAndUpdateRoomChatLog(String roomChatPk){
            List<RoomChatMessage> roomChatList=roomChatMessageRepository.findAllByRoomChatPk(roomChatPk);
            log.debug(roomChatList.toString());


    }
}
