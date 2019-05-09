package com.subjecttochange.ghhelper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subjecttochange.ghhelper.persistence.service.SessionService;
import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class EventController {

    public static final long ONE_DAY = 86400000L;
    private final SessionService sessionService;

    @Autowired
    public EventController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/stream-sse-mvc")
    public SseEmitter streamSseMvc() {
        SseEmitter emitter = new SseEmitter(ONE_DAY);
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        ObjectMapper objectMapper = new ObjectMapper();

        sseMvcExecutor.execute(() -> {
            try {
                for (int i = 0; true; i++) {
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .data("SSE MVC - " + JsonUtils.prettyPrint(objectMapper.writeValueAsString(
                                    sessionService.getRooms("1b6e045", null))
                            ))
                            .id(String.valueOf(i))
                            .name("message");
                    emitter.send(event);
                    Thread.sleep(1000);
                }
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }
}
