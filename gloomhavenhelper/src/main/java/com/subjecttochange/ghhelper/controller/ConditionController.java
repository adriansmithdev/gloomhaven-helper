package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Condition;
import com.subjecttochange.ghhelper.persistence.repository.ConditionRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@ToString
public class ConditionController {

    private ConditionRepository conditionRepository;
    private static final String NOT_FOUND = "Status not found with id ";

    @Autowired
    public ConditionController(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @GetMapping("/condition")
    @ResponseBody
    public Page<Condition> getRooms(@RequestParam(value = "id", required = false) Long id, Pageable pageable) {
        if (id == null) {
            return conditionRepository.findAll(pageable);
        } else {
            return new PageImpl<>(Collections.singletonList(
                    conditionRepository.findById(id).orElseThrow(() ->
                            new ResourceNotFoundException(NOT_FOUND + id))));
        }
    }

    @PostMapping("/condition")
    @ResponseBody
    public Condition createRoom(@Valid @RequestBody Condition condition) {
        return conditionRepository.save(condition);
    }

    @PutMapping("/condition")
    @ResponseBody
    public Condition updateRoom(@RequestParam(value = "id") Long id, @Valid @RequestBody Condition condition) {
        Condition conditionResult = conditionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(NOT_FOUND + id));
        conditionResult.setName(condition.getName());
        conditionResult.setTooltip(condition.getTooltip());
        conditionResult = conditionRepository.save(conditionResult);
        return conditionResult;
    }

    @DeleteMapping("/condition")
    public ResponseEntity<?> deleteRoom(@RequestParam(value = "id") Long id) {
        conditionRepository.delete(conditionRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(NOT_FOUND + id)));
        return ResponseEntity.ok().build();
    }
}

