package com.toki.backend.message.service;


import com.toki.backend.common.utils.ConvertUserTag;
import com.toki.backend.member.entity.User;
import com.toki.backend.member.repository.UserRepository;
import com.toki.backend.message.dto.response.MessageResponseDto;
import com.toki.backend.message.dto.request.MessageSendRequestDto;
import com.toki.backend.message.entity.Message;
import com.toki.backend.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public Page<MessageResponseDto> getMessageListByToUser(User toUser, int page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return messageRepository.findAllByToUser(toUser, pageRequest);
    }

    public void saveMessage(MessageSendRequestDto messageSendRequestDto,
                            String fromUserPk) {

        User fromUser = userRepository.findById(fromUserPk).get();
        User toUser = userRepository.findByUserTag(
                ConvertUserTag.convertUserTag(
                        messageSendRequestDto.getToUserTag())).get();
        messageRepository.save(Message.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .content(messageSendRequestDto.getContent())
                .build());
    }

    public Message getMessageByMessagePk(Long messagePk) {
        return messageRepository.findById(messagePk).orElse(null);
    }


    // 메세지 삭제
    public void deleteMessage(Long messagePk) {
        Message message = messageRepository.findById(messagePk).get();
        messageRepository.delete(message);
    }

}
