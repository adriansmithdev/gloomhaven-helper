package com.subjecttochange.ghhelper.persistence.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.subjecttochange.ghhelper.persistence.model.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class StreamService {

    public static final long FIVE_MINUTES = 300000L;

    private final SessionService sessionService;
    private Map<String, ConcurrentLinkedQueue<SseEmitter>> roomEmitters = new ConcurrentHashMap<>();
    private Map<String, String> roomCache = new ConcurrentHashMap<>();

    @Autowired
    public StreamService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public SseEmitter streamSseMvc(@RequestParam(value = "hash") String hash) {
        SseEmitter emitter = new SseEmitter(FIVE_MINUTES);
        setEmitterEvents(emitter, hash);
        saveEmitter(hash, emitter);

        if (!roomCache.containsKey(hash)) {
            String resultBody = jsonOutput(EventType.GET_SESSION, sessionService.getRooms(hash, null));
            roomCache.put(hash, resultBody);
        }

        sendBroadcast(emitter, roomCache.get(hash), hash);
        return emitter;
    }

    public void newEvent(EventType eventType, String hash, Object body) {
        if (roomEmitters.containsKey(hash)) {
            String newRoomResultBody = jsonOutput(EventType.GET_SESSION, sessionService.getRooms(hash, null));
            roomCache.put(hash, newRoomResultBody);

            String resultBody = jsonOutput(eventType, body);
            for (SseEmitter emitter : roomEmitters.get(hash)) {
                sendBroadcast(emitter, resultBody, hash);
            }
        }
    }

    private void sendBroadcast(SseEmitter emitter, String body, String hash) {
        SseEmitter.SseEventBuilder event = SseEmitter.event()
                .data(body)
                .name("message");
        try {
            emitter.send(event);
        } catch (IOException e) {
            deleteEmitter(hash, emitter);
            emitter.completeWithError(e);
            System.out.println("Status: Deleted old emitter");
        }
    }

    public String jsonOutput(EventType eventType, Object room) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String result = "{\"action\":\"" + eventType.name() + "\",\"response\":";
        try {
            result += objectMapper.writeValueAsString(room);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result + "}";
    }

    private void deleteEmitter(String hash, SseEmitter emitter) {
        if (roomEmitters.containsKey(hash)) {
            roomEmitters.get(hash).remove(emitter);
        }
    }

    private void saveEmitter(String hash, SseEmitter emitter) {
        if (roomEmitters.containsKey(hash)) {
            ConcurrentLinkedQueue<SseEmitter> emitters = roomEmitters.get(hash);
            emitters.add(emitter);
        } else {
            roomEmitters.put(hash, new ConcurrentLinkedQueue<>(Collections.singletonList(emitter)));
        }
    }

    private void setEmitterEvents(SseEmitter sseEmitter, String hash) {
        sseEmitter.onCompletion(() -> {
            deleteEmitter(hash, sseEmitter);
            System.out.println("Emitter has completed");
        });
        sseEmitter.onTimeout(() -> {
            deleteEmitter(hash, sseEmitter);
            System.out.println("Emitter has timed out");
        });
        sseEmitter.onError((e) -> {
            deleteEmitter(hash, sseEmitter);
            System.out.println("Emitter has errored");
        });
    }

}
