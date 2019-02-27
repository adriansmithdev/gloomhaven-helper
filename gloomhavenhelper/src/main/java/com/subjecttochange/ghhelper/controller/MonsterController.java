package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.jsonio.requestbodies.MonsterRequestBody;
import com.subjecttochange.ghhelper.persistence.model.jsonio.requestbodies.RoomRequestBody;
import com.subjecttochange.ghhelper.persistence.model.jsonio.responsebodies.MonsterResponseBody;
import com.subjecttochange.ghhelper.persistence.model.jsonio.responsebodies.RoomResponseBody;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
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
public class MonsterController {

    private static final String NOT_FOUND = "Monster not found with id ";

    @Autowired
    private MonsterRepository monsterRepository;

    @GetMapping("/monsters")
    @ResponseBody
    public Page<MonsterResponseBody>
    getMonsters(@RequestParam(value = "id", required = false) Long id, Pageable pageable) {
        Page<Monster> monsters;
        if (id == null) {
            monsters = monsterRepository.findAll(pageable);
        } else {
            Monster monster = monsterRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException(NOT_FOUND + id));
            monsters = new PageImpl<>(Collections.singletonList(monster));
        }

        ArrayList<MonsterResponseBody> monsterResponseBodies = new ArrayList<>();

        for (Monster monster : monsters) {
            monsterResponseBodies.add(MonsterResponseBody.create(monster));
        }

        return new PageImpl<>(monsterResponseBodies);
    }

    @PostMapping("/monsters")
    @ResponseBody
    public MonsterResponseBody createMonster(@Valid @RequestBody(required = false) MonsterRequestBody monsterRequest) {
        Monster monster = Monster.create("", 0);
        monsterRequest.updateMonster(monster);
        monster = monsterRepository.save(monster);
        return MonsterResponseBody.create(monster);
    }

    @PutMapping("/monsters")
    @ResponseBody
    public MonsterResponseBody updateMonster(@RequestParam(value = "id") Long id,
                                       @Valid @RequestBody(required = false) MonsterRequestBody monsterRequest) {
        Monster monster = monsterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));
        monsterRequest.updateMonster(monster);
        monster = monsterRepository.save(monster);
        return MonsterResponseBody.create(monster);
    }

    @DeleteMapping("/monsters")
    public ResponseEntity<?> deleteMonster(@RequestParam(value = "id") Long id) {
        monsterRepository.delete(monsterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id)));
        return ResponseEntity.ok().build();
    }
}
