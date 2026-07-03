package com.example.springwebrtc;

import com.example.springwebrtc.model.SignalMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class SignalingHandler extends TextWebSocketHandler {
    private final RoomRegistry rooms = new RoomRegistry();
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        rooms.leaveAll(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        var msg = objectMapper.readValue(message.getPayload(), SignalMessage.class);

        switch(msg.getType()) {
            case join -> rooms.join(msg.getRoom(), session);
            case offer, answer, ice -> {
                var maybePeer = rooms.otherInRoom(msg.getRoom(), session);
                if(maybePeer.isPresent() && maybePeer.get().isOpen()) {
                    maybePeer.get().sendMessage(new TextMessage(objectMapper.writeValueAsString(msg)));
                }
            }
            case leave -> rooms.leaveAll(session);
        }
    }


}
