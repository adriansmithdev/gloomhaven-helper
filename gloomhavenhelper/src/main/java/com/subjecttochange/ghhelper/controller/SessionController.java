package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.persistence.model.responsebodies.SessionResponseBody;
import com.subjecttochange.ghhelper.persistence.service.SessionService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ToString
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/sessions")
    @ResponseBody
    public Page<SessionResponseBody> getRooms(@RequestParam(value = "hash", required = false) String hash,
                                              Pageable pageable) {
        return sessionService.getRooms(hash, pageable);
    }
}

