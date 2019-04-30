package com.subjecttochange.ghhelper.persistence.service;

import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.DeckInstance;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.ActionDeck;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.repository.DeckRepository;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Map<Monster, DeckInstance> drawMonsterActions(Room room) {
        room.setDecks(getMonsterActionDecks(room));

        for (DeckInstance deckInstance : room.getDecks().values()) {
            deckInstance.drawAction();
        }

        return room.getDecks();
    }

    public Map<Monster, DeckInstance> getMonsterActionDecks(Room room) {
        Set<Monster> monsters = new HashSet<>();

        for (MonsterInstance monsterInstance : room.getMonsterInstances()) {
            monsters.add(monsterInstance.getMonster());
        }

        for (Monster monster : monsters) {
            if (!room.getDecks().containsKey(monster)) {
                room.getDecks().put(monster, getMonsterActionDeck(monster));
            }
        }

        return room.getDecks();
    }

    private DeckInstance getMonsterActionDeck(Monster monster){
        ActionDeck actionDeck = deckRepository.findByMonsterName(monster.getName());
        return DeckInstance.create(actionDeck);
    }
}
