package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.service.MonsterService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ToString
public class MonsterController {

    private final MonsterService monsterService;

    @Autowired
    public MonsterController(MonsterService monsterService) {
        this.monsterService = monsterService;
    }

    @GetMapping("/monsters")
    @ResponseBody
    public Page<Monster> getMonsters(@RequestParam(value = "id", required = false) Long id, Pageable pageable) {
        return monsterService.getMonsters(id, pageable);
    }

    @PostMapping("/monsters")
    @ResponseBody
    public Monster createMonster(@Valid @RequestBody(required = false) Monster monsterRequest) {
        return monsterService.createMonster(monsterRequest);
    }

    @PutMapping("/monsters")
    @ResponseBody
    public Monster updateMonster(@RequestParam(value = "id") Long id,
                                 @Valid @RequestBody(required = false) Monster monsterRequest) {
        return monsterService.updateMonster(id, monsterRequest);
    }

    @DeleteMapping("/monsters")
    public ResponseEntity<?> deleteMonster(@RequestParam(value = "id") Long id) {
        return monsterService.deleteMonster(id);
    }
}
