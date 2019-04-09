package com.subjecttochange.ghhelper.persistence.service;

import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.repository.MonsterInstanceRepository;
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
public class MonsterInstanceService {

    private final RoomRepository roomRepository;
    private final MonsterRepository monsterRepository;
    private final MonsterInstanceRepository monsterInstanceRepository;

    @Autowired
    public MonsterInstanceService(RoomRepository roomRepository, MonsterRepository monsterRepository,
                                     MonsterInstanceRepository monsterInstanceRepository) {
        this.roomRepository = roomRepository;
        this.monsterRepository = monsterRepository;
        this.monsterInstanceRepository = monsterInstanceRepository;
    }

    @Transactional
    public Page<MonsterInstance> getMonsters(String hash, Long id, Pageable pageable) {
        if (id == null) {
            return monsterInstanceRepository.findByRoomHash(hash, pageable);
        } else {
            MonsterInstance monsterInstance = monsterInstanceRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException(Errors.NO_ID_INSTANCE + id));
            MonsterInstance.checkHashMatchesGiven(monsterInstance, hash, id);
            return new PageImpl<>(Collections.singletonList(monsterInstance));
        }
    }

    @Transactional
    public MonsterInstance createMonster(String hash, MonsterInstance request) {
        Room room = roomRepository.findByHash(hash)
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_HASH_ROOM + request.getMonsterId()));
        Monster monster = monsterRepository.findById(request.getMonsterId())
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_MONSTER + request.getMonsterId()));

        MonsterInstance monsterInstance;
        Boolean eliteStatus = request.getIsElite();
        if (eliteStatus != null && eliteStatus) {
            monsterInstance = MonsterInstance.create(true, room, monster);
        } else {
            monsterInstance = MonsterInstance.create(false, room, monster);
        }
        monsterInstance = monsterInstance.updateMonsterInstance(request);
        int token = monsterInstance.nextAvailableToken(room.getMonsterInstances());
        monsterInstance.setToken(token);
        return monsterInstanceRepository.save(monsterInstance);
    }

    @Transactional
    public MonsterInstance updateMonster(String hash, Long id, MonsterInstance request) {
        MonsterInstance monsterInstance = monsterInstanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_INSTANCE + id));
        Monster monster = monsterRepository.findById(request.getMonsterId())
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_MONSTER + request.getMonsterId()));

        MonsterInstance.checkHashMatchesGiven(monsterInstance, hash, id);

        monsterInstance.setMonster(monster);
        monsterInstance.setMonsterId(monster.getId());
        monsterInstance = monsterInstance.updateMonsterInstance(request);
        return monsterInstanceRepository.save(monsterInstance);
    }

    @Transactional
    public ResponseEntity<?> deleteMonster(String hash, Long id) {
        MonsterInstance monsterInstance = monsterInstanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_MONSTER + id));
        MonsterInstance.checkHashMatchesGiven(monsterInstance, hash, id);

        monsterInstanceRepository.delete(monsterInstance);
        return ResponseEntity.ok().build();
    }
}
