package com.example.websocketoverflow;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class OverflowingWebsocketConfig implements WebSocketConfigurer {
    public final OverflowingWebsocketHandler overflowingHandler;

    public OverflowingWebsocketConfig(OverflowingWebsocketHandler overflowingHandler) {
        this.overflowingHandler = overflowingHandler;
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(overflowingHandler, "/overflowingwebsocket").setAllowedOrigins("*");
    }
}
