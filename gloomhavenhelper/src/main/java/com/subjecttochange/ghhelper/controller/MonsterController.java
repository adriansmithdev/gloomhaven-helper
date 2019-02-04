package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.persistence.model.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.repository.MonsterInstancesRepository;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MonsterController {

    @Autowired
    private MonsterRepository monsterRepository;
    @Autowired
    private MonsterInstancesRepository instancesRepository;

    @PostMapping("/monster/{monsterInstanceID}")
    public void updateMonster(@Valid @PathVariable Long monsterInstanceID,
                              @Valid @RequestBody MonsterInstance monsterInstance) {
        instancesRepository.save(monsterInstance);
    }

}
