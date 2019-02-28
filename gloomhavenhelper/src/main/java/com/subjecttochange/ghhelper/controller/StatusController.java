package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.jsonio.requestbodies.RoomRequestBody;
import com.subjecttochange.ghhelper.persistence.model.jsonio.responsebodies.RoomResponseBody;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.StatusEffect;
import com.subjecttochange.ghhelper.persistence.repository.StatusRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@ToString
public class StatusController {

    private StatusRepository statusRepository;
    private static String NOT_FOUND = "Status not found with id ";

    @Autowired
    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @GetMapping("/status")
    @ResponseBody
    public Page<StatusEffect> getRooms(@RequestParam(value = "id", required = false) Long id, Pageable pageable) {
        if (id == null) {
            return statusRepository.findAll(pageable);
        } else {
            return new PageImpl<>(Collections.singletonList(
                    statusRepository.findById(id).orElseThrow(() ->
                            new ResourceNotFoundException(NOT_FOUND + id))));
        }
    }

    @PostMapping("/status")
    @ResponseBody
    public StatusEffect createRoom(@Valid @RequestBody StatusEffect statusEffect) {
        return statusRepository.save(statusEffect);
    }

    @PutMapping("/status")
    @ResponseBody
    public StatusEffect updateRoom(@RequestParam(value = "id") Long id, @Valid @RequestBody StatusEffect statusEffect) {
        StatusEffect statusEffectResult = statusRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(NOT_FOUND + id));
        statusEffectResult.setName(statusEffect.getName());
        statusEffectResult.setTooltip(statusEffect.getTooltip());
        statusEffectResult = statusRepository.save(statusEffectResult);
        return statusEffectResult;
    }

    @DeleteMapping("/status")
    public ResponseEntity<?> deleteRoom(@RequestParam(value = "id") Long id) {
        statusRepository.delete(statusRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(NOT_FOUND + id)));
        return ResponseEntity.ok().build();
    }
}
