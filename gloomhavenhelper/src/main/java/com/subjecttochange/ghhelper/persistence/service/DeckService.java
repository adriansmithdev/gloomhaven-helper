package com.subjecttochange.ghhelper.persistence.service;

import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterAction;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterActionDeck;
import com.subjecttochange.ghhelper.persistence.repository.DeckRepository;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DeckService {

    private final DeckRepository deckRepository;
    private final MonsterRepository monsterRepository;

    @Autowired
    public DeckService(DeckRepository deckRepository, MonsterRepository monsterRepository) {
        this.deckRepository = deckRepository;
        this.monsterRepository = monsterRepository;
    }

    public List<MonsterActionDeck> getMonsterActionDecks(List<Monster> monsters) {
        List<String> monsterNames = new ArrayList<>();

        for (Monster monster : monsters) {
            monsterNames.add(monster.getName());
        }

        return deckRepository.findAllByMonsterName(monsterNames);
    }

    public Map<Monster, MonsterAction> drawMonsterActions(Room room, List<Monster> monsters) {
        Map<Monster, MonsterActionDeck> roomDecks = room.getDecks();

        for (Monster monster : monsters) {
            if (!roomDecks.containsKey(monster)) {
                MonsterActionDeck seededDeck = deckRepository.findByMonsterName(monster.getName());
                roomDecks.put(monster, MonsterActionDeck.createDeck(monster.getName(), seededDeck.getActionDeck()));
            }
        }

        for (MonsterActionDeck monsterActionDeck : roomDecks.values()) {
            monsterActionDeck.drawNewMonsterAction();
        }

        return null;
    }
}
