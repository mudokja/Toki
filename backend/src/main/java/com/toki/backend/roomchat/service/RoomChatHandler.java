package com.toki.backend.roomchat.service;

import com.toki.backend.common.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomChatHandler implements ChannelInterceptor {
    private final TokenProvider tokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.debug("메시지 : {}, 채널 {},",message.toString(),message.getHeaders());
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        if(accessor.getCommand() == StompCommand.CONNECT) {
//            String jwt = TokenProvider.resolveToken(accessor.getFirstNativeHeader("Authorization"));
//            if(!tokenProvider.validateAccessToken(jwt)){
//                try {
//                    Authentication authentication = tokenProvider.getAuthentication(jwt);
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    log.debug("웹소켓 '{}' 인증 정보를 저장했습니다,", authentication.getName());
//                    throw new AccessDeniedException("채팅 인증 오류");
//                } catch (AccessDeniedException e) {
//                    log.error(e.getMessage());
//                }
//            }
//        }
        return message;
    }
}
