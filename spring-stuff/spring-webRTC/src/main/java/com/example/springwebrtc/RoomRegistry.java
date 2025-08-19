package com.example.springwebrtc;

import org.springframework.web.socket.WebSocketSession;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.*;

public class RoomRegistry {
    private final ConcurrentHashMap<String, Set<WebSocketSession>> rooms = new ConcurrentHashMap<>();

    public void join(String roomId, WebSocketSession session) {
        rooms.computeIfAbsent(roomId, r -> ConcurrentHashMap.newKeySet()).add(session);
    }

    public void leaveAll(WebSocketSession session) {
        rooms.values().forEach(set -> set.remove(session));
    }

    public Optional<WebSocketSession> otherInRoom(String room, WebSocketSession sender) {
        var set = rooms.getOrDefault(room, Set.of());
        return set.stream()
                .filter(s -> !s.getId().equals(sender.getId()))
                .findFirst();
    }

}
