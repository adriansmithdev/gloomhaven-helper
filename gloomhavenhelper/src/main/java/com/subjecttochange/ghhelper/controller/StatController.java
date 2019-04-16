package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.persistence.model.orm.Stat;
import com.subjecttochange.ghhelper.persistence.service.StatService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ToString
public class StatController {

    private final StatService statService;

    @Autowired
    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping("/stats")
    @ResponseBody
    public Page<Stat> getStat(@RequestParam(value = "id", required = false) Long id, Pageable pageable) {
        return statService.getStat(id, pageable);
    }

    @PostMapping("/stats")
    @ResponseBody
    public Stat createStat(@Valid @RequestBody Stat statRequest) {
        return statService.createStat(statRequest);
    }

    @PutMapping("/stats")
    @ResponseBody
    public Stat updateStat(@RequestParam(value = "id") Long id, @Valid @RequestBody Stat statRequest) {
        return statService.updateStat(id, statRequest);
    }

    @DeleteMapping("/stats")
    public ResponseEntity<?> deleteStat(@RequestParam(value = "id") Long id) {
        return statService.deleteStat(id);
    }
}