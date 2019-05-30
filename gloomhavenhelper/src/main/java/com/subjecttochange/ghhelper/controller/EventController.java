package com.subjecttochange.ghhelper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.subjecttochange.ghhelper.persistence.model.EventType;
import com.subjecttochange.ghhelper.persistence.service.SessionService;
import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Controller
public class EventController {

    public static final long FIVE_MINUTES = 300000L;
    private final SessionService sessionService;
    private Map<String, LinkedList<SseEmitter>> roomEmitters = new HashMap<>();
    private Map<String, String> roomCache = new HashMap<>();

    @Autowired
    public EventController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/stream")
    public SseEmitter streamSseMvc(@RequestParam(value = "hash") String hash) {
        SseEmitter emitter = new SseEmitter(FIVE_MINUTES);

        setEmitterEvents(emitter, hash);
        saveEmitter(hash, emitter);

        if (!roomCache.containsKey(hash)) {
            String resultBody = jsonOutput(EventType.GET_SESSION, sessionService.getRooms(hash, null));
            roomCache.put(hash, resultBody);
        }

        try {
            sendBroadcast(emitter, roomCache.get(hash), hash);
        } catch (Exception e) {
            System.out.println("Error sending message!");
        }

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
            LinkedList<SseEmitter> emitters = roomEmitters.get(hash);
            emitters.add(emitter);
        } else {
            roomEmitters.put(hash, new LinkedList<>(Collections.singletonList(emitter)));
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
