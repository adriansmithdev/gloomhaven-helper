package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.repository.MonsterInstanceRepository;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

/**
 * @author subjecttochange
 * @version 1
 *
 * Controls restful responses to monster instances
 */
@RestController
@ToString
public class MonsterInstanceController {

    private RoomRepository roomRepository;
    private MonsterRepository monsterRepository;
    private MonsterInstanceRepository monsterInstanceRepository;

    @Autowired
    public MonsterInstanceController(RoomRepository roomRepository, MonsterRepository monsterRepository,
                                     MonsterInstanceRepository monsterInstanceRepository) {
        this.roomRepository = roomRepository;
        this.monsterRepository = monsterRepository;
        this.monsterInstanceRepository = monsterInstanceRepository;
    }

    @GetMapping("/monsterinstances")
    @ResponseBody
    public Page<MonsterInstance> getMonsterInstances(@RequestParam(value = "hash") String hash,
                                                     @RequestParam(value = "id", required = false) Long id,
                                                     Pageable pageable) {
        if (id == null) {
            return monsterInstanceRepository.findByRoomHash(hash, pageable);
        } else {
            MonsterInstance monsterInstance = monsterInstanceRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException(Errors.NO_ID_INSTANCE + id));
            MonsterInstance.checkHashMatchesGiven(monsterInstance, hash, id);
            return new PageImpl<>(Collections.singletonList(monsterInstance));
        }
    }

    @PostMapping("/monsterinstances")
    public MonsterInstance createMonsterInstance(@RequestParam(value = "hash") String hash,
                                                 @Valid @RequestBody(required = false) MonsterInstance request) {
        Room room = roomRepository.findByHash(hash)
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_HASH_ROOM + request.getMonsterId()));
        Monster monster = monsterRepository.findById(request.getMonsterId())
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_MONSTER + request.getMonsterId()));

        MonsterInstance monsterInstance = MonsterInstance.create(room, monster);
        monsterInstance = monsterInstance.updateMonsterInstance(request);
        return monsterInstanceRepository.save(monsterInstance);
    }

    @PutMapping("/monsterinstances")
    public MonsterInstance updateMonsterInstance(@RequestParam(value = "hash") String hash,
                                                 @RequestParam(value = "id") Long id,
                                                 @Valid @RequestBody(required = false) MonsterInstance request) {
        MonsterInstance monsterInstance = monsterInstanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_INSTANCE + id));
        Monster monster = monsterRepository.findById(request.getMonsterId())
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_MONSTER + request.getMonsterId()));

        MonsterInstance.checkHashMatchesGiven(monsterInstance, hash, id);

        monsterInstance.setMonster(monster);
        monsterInstance = monsterInstance.updateMonsterInstance(request);
        return monsterInstanceRepository.save(monsterInstance);
    }

    @DeleteMapping("/monsterinstances")
    public ResponseEntity<?> deleteMonsterInstance(@RequestParam(value = "hash") String hash,
                                                   @RequestParam(value = "id") Long id) {
        MonsterInstance monsterInstance = monsterInstanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_MONSTER + id));
        MonsterInstance.checkHashMatchesGiven(monsterInstance, hash, id);

        monsterInstanceRepository.delete(monsterInstance);
        return ResponseEntity.ok().build();
    }

}
