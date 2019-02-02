package com.subjecttochange.ghhelper.controller;

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

    @PostMapping("/monster/{monsterID}/")
    public void updateMonster(@Valid @PathVariable int monsterID, @RequestBody String bodyJson) {
        //TODO parse Post Json data
        //monsterRepository.findById(//monsterID);
    }
}
