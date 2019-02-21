package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MonsterController {

    private static final String NOT_FOUND = "Monster not found with id ";

    @Autowired
    private MonsterRepository monsterRepository;

    @GetMapping("/monsters")
    public Page<Monster> getMonsters(Pageable pageable) {
        return monsterRepository.findAll(pageable);
    }

    @GetMapping("/monsters/{id}")
    public Monster getMonster(@PathVariable Long id) {
        return monsterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));
    }

    @PostMapping("/monsters")
    public Monster createMonster(@Valid @RequestBody Monster monster) {
        return monsterRepository.save(monster);
    }

    @PutMapping("/monsters/{id}")
    public Monster updateMonster(@PathVariable Long id, @Valid @RequestBody Monster monsterRequest) {
        return monsterRepository.findById(id).map(monster -> {
                    monster.setName(monsterRequest.getName());
                    monster.setMaxHealth(monsterRequest.getMaxHealth());
                    monster.setMoveRange(monsterRequest.getMoveRange());
                    return monsterRepository.save(monster);
                }).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));
    }

    @DeleteMapping("/monsters/{id}")
    public ResponseEntity<?> deleteMonster(@PathVariable Long id) {
        return monsterRepository.findById(id).map(monster -> {
                    monsterRepository.delete(monster);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));
    }

}
