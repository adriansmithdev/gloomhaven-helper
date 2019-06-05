package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.persistence.model.EventType;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.model.responsebodies.DeleteIdResponseBody;
import com.subjecttochange.ghhelper.persistence.service.MonsterInstanceService;
import com.subjecttochange.ghhelper.persistence.service.StreamService;
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
    private final StreamService streamService;

    @Autowired
    public MonsterInstanceController(MonsterInstanceService monsterInstanceService, StreamService streamService) {
        this.monsterInstanceService = monsterInstanceService;
        this.streamService = streamService;
    }

    @GetMapping("/monsterinstances")
    @ResponseBody
    public Page<MonsterInstance> getMonsterInstances(@RequestParam(value = "hash") String hash,
                                                     @RequestParam(value = "id", required = false) Long id,
                                                     Pageable pageable) {
        return monsterInstanceService.getMonsters(hash, id, pageable);
    }

    @PostMapping("/monsterinstances")
    @ResponseBody
    public MonsterInstance createMonsterInstance(@RequestParam(value = "hash") String hash,
                                                 @Valid @RequestBody(required = false) MonsterInstance request) {
        MonsterInstance monsterInstance = monsterInstanceService.createMonster(hash, request);
        streamService.newEvent(EventType.POST_MONSTER_INSTANCE, hash, monsterInstance);
        return monsterInstance;
    }

    @PutMapping("/monsterinstances")
    @ResponseBody
    public MonsterInstance updateMonsterInstance(@RequestParam(value = "hash") String hash,
                                                 @RequestParam(value = "id") Long id,
                                                 @Valid @RequestBody(required = false) MonsterInstance request) {
        MonsterInstance monsterInstance = monsterInstanceService.updateMonster(hash, id, request);
        streamService.newEvent(EventType.PUT_MONSTER_INSTANCE, hash, monsterInstance);
        return monsterInstance;
    }

    @DeleteMapping("/monsterinstances")
    public ResponseEntity<?> deleteMonsterInstance(@RequestParam(value = "hash") String hash,
                                                   @RequestParam(value = "id") Long id) {
        ResponseEntity<?> response = monsterInstanceService.deleteMonster(hash, id);
        streamService.newEvent(EventType.DELETE_MONSTER_INSTANCE, hash, DeleteIdResponseBody.create(id));
        return response;
    }

}
