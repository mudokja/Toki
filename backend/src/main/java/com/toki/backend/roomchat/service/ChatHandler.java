package com.toki.backend.roomchat.service;

import com.toki.backend.common.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatHandler implements ChannelInterceptor {
    private final TokenProvider tokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if(accessor.getCommand() == StompCommand.CONNECT) {
            String token = TokenProvider.resolveToken(accessor.getFirstNativeHeader("Authorization"));
            if(!tokenProvider.validateAccessToken(token)){
                try {
                    throw new AccessDeniedException("채팅 인증 오류");
                } catch (AccessDeniedException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return message;
    }
}
