package com.example.springwebsockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);
        ChatMessage normalizedMessage = new ChatMessage(
                normalize(chatMessage.sender(), "anonymous"),
                normalize(chatMessage.content(), "")
        );

        if (normalizedMessage.content().isBlank()) {
            return;
        }

        String payload = toJson(normalizedMessage);
        TextMessage outbound = new TextMessage(payload);

        for (WebSocketSession activeSession : sessions) {
            if (activeSession.isOpen()) {
                activeSession.sendMessage(outbound);
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    private String normalize(String value, String fallback) {
        if (value == null) {
            return fallback;
        }

        String trimmed = value.trim();
        return trimmed.isEmpty() ? fallback : trimmed;
    }

    private String toJson(ChatMessage message) throws JsonProcessingException {
        return objectMapper.writeValueAsString(message);
    }
}
