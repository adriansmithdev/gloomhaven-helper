package com.subjecttochange.ghhelper.persistence.service;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.ActionDeck;
import com.subjecttochange.ghhelper.persistence.repository.DeckRepository;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeckService {

    private final DeckRepository deckRepository;
    private final MonsterRepository monsterRepository;

    @Autowired
    public DeckService(DeckRepository deckRepository, MonsterRepository monsterRepository) {
        this.deckRepository = deckRepository;
        this.monsterRepository = monsterRepository;
    }

    public List<ActionDeck> getMonsterActionDecks(List<Monster> monsters) {
        List<String> monsterNames = new ArrayList<>();

        for (Monster monster : monsters) {
            monsterNames.add(monster.getName());
        }

        return deckRepository.findAllByMonsterName(monsterNames);
    }

    public ActionDeck getMonsterActionDeck(ActionDeck deck){
        return
    }




}
