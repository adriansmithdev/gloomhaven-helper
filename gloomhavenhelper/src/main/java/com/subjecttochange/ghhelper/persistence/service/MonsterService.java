package com.subjecttochange.ghhelper.persistence.service;

import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.responsebodies.MonsterActionResponseBody;
import com.subjecttochange.ghhelper.persistence.model.responsebodies.MonsterResponseBody;
import com.subjecttochange.ghhelper.persistence.model.responsebodies.SingleActionResponseBody;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class MonsterService {

    private final MonsterRepository monsterRepository;
    private final RoomRepository roomRepository;
    private final DeckService deckService;

    @Autowired
    public MonsterService(MonsterRepository monsterRepository, RoomRepository roomRepository, DeckService deckService) {
        this.monsterRepository = monsterRepository;
        this.roomRepository = roomRepository;
        this.deckService = deckService;
    }

    @Transactional
    public Page<Monster> getMonsters(Long id, Pageable pageable) {
        if (id == null) {
            return monsterRepository.findAll(pageable);
        } else {
            Monster monster = monsterRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException(Errors.NO_ID_MONSTER + id));
            return new PageImpl<>(Collections.singletonList(monster));
        }
    }

    @Transactional
    public Monster createMonster(Monster monsterRequest) {
        Monster monster = Monster.create("", 0);
        monster = monster.updateMonster(monsterRequest);
        return monsterRepository.save(monster);
    }

    @Transactional
    public Monster updateMonster(Long id, Monster monsterRequest) {
        Monster monster = monsterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_MONSTER + id));
        monster = monster.updateMonster(monsterRequest);
        return monsterRepository.save(monster);
    }

    @Transactional
    public ResponseEntity<?> deleteMonster(Long id) {
        monsterRepository.delete(monsterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_MONSTER + id)));
        return ResponseEntity.ok().build();
    }

    @Transactional
    public SingleActionResponseBody drawAction(String hash, Long id) {
        Room room = roomRepository.findByHash(hash).orElseThrow(() -> new ResourceNotFoundException(Errors.NO_HASH_ROOM + hash));
        Monster monster = monsterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_MONSTER + id));

        deckService.drawSingleMonsterAction(room, monster);

        SingleActionResponseBody singleResponse = SingleActionResponseBody.create(id);
        MonsterActionResponseBody monsterResponse = MonsterActionResponseBody.create(room.getDecks().get(monster).getCurrentAction());
        singleResponse.setMonsterActionResponseBody(monsterResponse);

        return singleResponse;
    }
}
