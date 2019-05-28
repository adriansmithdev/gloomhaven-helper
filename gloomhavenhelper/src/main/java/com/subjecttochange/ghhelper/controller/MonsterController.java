package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.responsebodies.MonsterActionResponseBody;
import com.subjecttochange.ghhelper.persistence.model.responsebodies.MonsterResponseBody;
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


    @PostMapping("/monster/draw")
    @ResponseBody
    public MonsterActionResponseBody drawAction(@RequestParam(value = "hash") String hash,
                                                @Valid @RequestBody Long monsterId) {
        return monsterService.drawAction(hash, monsterId);
    }
}
