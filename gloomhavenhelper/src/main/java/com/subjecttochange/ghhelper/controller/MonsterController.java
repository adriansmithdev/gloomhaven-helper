package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.persistence.model.EventType;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.requestbodies.MonsterIdRequestBody;
import com.subjecttochange.ghhelper.persistence.model.responsebodies.SingleActionResponseBody;
import com.subjecttochange.ghhelper.persistence.service.MonsterService;
import com.subjecttochange.ghhelper.persistence.service.StreamService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ToString
public class MonsterController {

    private final MonsterService monsterService;
    private final StreamService streamService;

    @Autowired
    public MonsterController(MonsterService monsterService, StreamService streamService) {
        this.monsterService = monsterService;
        this.streamService = streamService;
    }

    @GetMapping("/monsters")
    @ResponseBody
    public Page<Monster> getMonsters(@RequestParam(value = "id", required = false) Long id, Pageable pageable) {
        return monsterService.getMonsters(id, pageable);
    }


    @PostMapping("/monster/draw")
    @ResponseBody
    public SingleActionResponseBody drawAction(@RequestParam(value = "hash") String hash,
                                                @Valid @RequestBody MonsterIdRequestBody monsterId) {
        SingleActionResponseBody response = monsterService.drawAction(hash, monsterId.getMonsterId());
        streamService.newEvent(EventType.POST_MONSTER_DRAW, hash, response);
        return response;
    }
}
