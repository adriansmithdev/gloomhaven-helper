package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.repository.MonsterInstanceRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author subjecttochange
 * @version 1
 *
 * Controls restful responses to monster instances
 */
@RestController
@ToString
public class MonsterInstanceController {

    @Autowired
    private MonsterInstanceRepository monsterInstanceRepository;

    @GetMapping("/monsterinstances")
    public Page<MonsterInstance> getMonsterInstances(Pageable pageable) {
        return monsterInstanceRepository.findAll(pageable);
    }

    @GetMapping("/monsterinstances/{id}")
    public MonsterInstance getMonsterInstances(@PathVariable Long monsterInstanceId) {
        return monsterInstanceRepository.findById(monsterInstanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Monster instance not found with id " + monsterInstanceId));
    }

    @PostMapping("/monsterinstances")
    public MonsterInstance createMonsterInstance(@Valid @RequestBody MonsterInstance monsterInstance) {
        return monsterInstanceRepository.save(monsterInstance);
    }

    @PutMapping("/monsterinstances/{monsterInstanceId}")
    public MonsterInstance updateMonsterInstance(@PathVariable Long monsterInstanceId,
                                                 @Valid @RequestBody MonsterInstance monsterRequest) {
        return monsterInstanceRepository.findById(monsterInstanceId)
                .map(monsterInstance -> {
                    monsterInstance.setCurrentHealth(monsterRequest.getCurrentHealth());
                    monsterInstance.setMaxHealth(monsterRequest.getMaxHealth());
                    return monsterInstanceRepository.save(monsterInstance);
                }).orElseThrow(() -> new ResourceNotFoundException("Monster instance not found with id " + monsterInstanceId));
    }

    @DeleteMapping("/monsterinstances/{monsterInstanceId}")
    public ResponseEntity<?> deleteMonsterInstance(@PathVariable Long monsterInstanceId) {
        return monsterInstanceRepository.findById(monsterInstanceId)
                .map(question -> {
                    monsterInstanceRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Monster instance not found with id " + monsterInstanceId));
    }
}
