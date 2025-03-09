package com.toki.backend.config;

import com.toki.backend.roomchat.service.RoomChatHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebStompConfig implements WebSocketMessageBrokerConfigurer {

    private final RoomChatHandler roomChatHandler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/subChat","/toChat");
        registry.setApplicationDestinationPrefixes("/pubChat");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat")
                .setAllowedOriginPatterns("*");
//                .withSockJS()
    }

    // 인증필터 예정지...
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration){
//        registration.interceptors(roomChatHandler);
//    }
}