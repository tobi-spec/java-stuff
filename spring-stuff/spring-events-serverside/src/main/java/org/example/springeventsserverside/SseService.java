package org.example.springeventsserverside;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseService {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public void addEmitter(SseEmitter sseEmitter) {
        emitters.add(sseEmitter);
        sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
        sseEmitter.onTimeout(() -> emitters.remove(sseEmitter));
    }

    @Scheduled(fixedRate = 1000)
    public void sendEvents() {
        for (SseEmitter sseEmitter: emitters) {
            try {
                sseEmitter.send(System.currentTimeMillis());
            } catch (IOException e) {
                sseEmitter.complete();
                emitters.remove(sseEmitter);
            }
        }
    }
}
