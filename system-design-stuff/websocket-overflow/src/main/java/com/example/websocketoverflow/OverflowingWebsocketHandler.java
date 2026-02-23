package com.example.websocketoverflow;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class OverflowingWebsocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws InterruptedException, IOException {
        Thread.sleep(25);
        session.sendMessage(new TextMessage("ok " + message));
    }


}
