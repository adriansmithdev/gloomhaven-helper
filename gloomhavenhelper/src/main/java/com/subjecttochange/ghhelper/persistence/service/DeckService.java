package com.subjecttochange.ghhelper.persistence.service;

import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.DeckInstance;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.ActionDeck;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.repository.DeckRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DeckService {

    private final DeckRepository deckRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public DeckService(DeckRepository deckRepository, RoomRepository roomRepository) {
        this.deckRepository = deckRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public void drawMonsterActions(Room room) {
        setupMonsterActionDecks(room);

        for (DeckInstance deckInstance : room.getDecks().values()) {
            deckInstance.drawAction();
        }
    }

    @Transactional
    public void drawSingleMonsterAction(Room room, Monster monster) {
        setupMonsterActionDecks(room);
        room.getDecks().get(monster).drawAction();
    }

    @Transactional
    public void setupMonsterActionDecks(Room room) {
        Set<Monster> monsters = new HashSet<>();
        Map<Monster, DeckInstance> decks = room.getDecks();

        for (MonsterInstance monsterInstance : room.getMonsterInstances()) {
            monsters.add(monsterInstance.getMonster());
        }

        // Deletes monsters that have been removed since the last run through
        for (Monster monster : decks.keySet()) {
            if (!monsters.contains(monster)) {
                decks.remove(monster);
            }
        }

        for (Monster monster : monsters) {
            if (!decks.containsKey(monster)) {
                decks.put(monster, getMonsterActionDeck(monster));
            }
        }
    }

    private DeckInstance getMonsterActionDeck(Monster monster){
        ActionDeck actionDeck = deckRepository.findByMonsterName(monster.getName());
        return DeckInstance.create(actionDeck);
    }
}
