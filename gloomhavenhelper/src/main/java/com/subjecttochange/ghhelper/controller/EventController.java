package com.subjecttochange.ghhelper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.subjecttochange.ghhelper.persistence.model.responsebodies.SessionResponseBody;
import com.subjecttochange.ghhelper.persistence.service.SessionService;
import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class EventController {

    public static final long ONE_DAY = 86400000L;
    private final SessionService sessionService;
    Map<String, LinkedList<SseEmitter>> observers = new HashMap<>();

    @Autowired
    public EventController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/stream-sse-mvc")
    public SseEmitter streamSseMvc(@RequestParam(value = "hash") String hash) {
        SseEmitter emitter = new SseEmitter(ONE_DAY);
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                //initial - send the whole
                updateEvent(
                        emitter,
                        jsonUpdate(sessionService.getRooms("1b6e045", null))
                );

                if (observers.containsKey(hash)) {
                    LinkedList<SseEmitter> emitters = observers.get(hash);
                    emitters.add(emitter);
                } else {
                    observers.put(hash, new LinkedList<>(Collections.singletonList(emitter)));
                }
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }

    // partial room change
    public void newEvent(String eventType, String hash, String body) {
        if (observers.containsKey(hash)) {
            for (SseEmitter emitter : observers.get(hash)) {
                SseEmitter.SseEventBuilder event = SseEmitter.event()
                        .data(body)
                        .name(eventType);
                try {
                    emitter.send(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateEvent(SseEmitter emitter, String content) throws IOException {
        SseEmitter.SseEventBuilder event = SseEmitter.event()
                .data(content)
                .name("message");
        emitter.send(event);
    }

    public String jsonUpdate(Object room) {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "";
        try {
            result = objectMapper.writeValueAsString(room);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return JsonUtils.prettyPrint(result);
    }
}
