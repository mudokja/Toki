package com.toki.backend.config;



import com.toki.backend.webrtc.service.RoomHandler;
import com.toki.backend.webrtc.service.TokiRoomManager;
import com.toki.backend.webrtc.service.UserRegistry;
import lombok.RequiredArgsConstructor;
import org.kurento.client.KurentoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {



    private final UserRegistry userRegistry;
    private final TokiRoomManager tokiRoomManager;



    @Bean
    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(32768);
        container.setMaxBinaryMessageBufferSize(32768);
        return container;
    }
    @Bean
    public RoomHandler roomHandler() {
        return new RoomHandler(tokiRoomManager,userRegistry);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(roomHandler(), "/ws/room").setAllowedOrigins("*");
    }
}
