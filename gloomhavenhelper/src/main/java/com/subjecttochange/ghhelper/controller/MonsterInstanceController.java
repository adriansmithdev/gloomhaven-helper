package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.service.MonsterInstanceService;
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

    private final MonsterInstanceService monsterInstanceService;

    @Autowired
    public MonsterInstanceController(MonsterInstanceService monsterInstanceService) {
        this.monsterInstanceService = monsterInstanceService;
    }

    @GetMapping("/monsterinstances")
    @ResponseBody
    public Page<MonsterInstance> getMonsterInstances(@RequestParam(value = "hash") String hash,
                                                     @RequestParam(value = "id", required = false) Long id,
                                                     Pageable pageable) {
        return monsterInstanceService.getMonsters(hash, id, pageable);
    }

    @PostMapping("/monsterinstances")
    public MonsterInstance createMonsterInstance(@RequestParam(value = "hash") String hash,
                                                 @Valid @RequestBody(required = false) MonsterInstance request) {
        return monsterInstanceService.createMonster(hash, request);
    }

    @PutMapping("/monsterinstances")
    public MonsterInstance updateMonsterInstance(@RequestParam(value = "hash") String hash,
                                                 @RequestParam(value = "id") Long id,
                                                 @Valid @RequestBody(required = false) MonsterInstance request) {
        return monsterInstanceService.updateMonster(hash, id, request);
    }

    @DeleteMapping("/monsterinstances")
    public ResponseEntity<?> deleteMonsterInstance(@RequestParam(value = "hash") String hash,
                                                   @RequestParam(value = "id") Long id) {
        return monsterInstanceService.deleteMonster(hash, id);
    }

}
