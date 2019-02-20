package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.repository.MonsterInstanceRepository;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author subjecttochange
 * @version 1
 *
 * Controls restful responses to monster instances
 */
@RestController
@ToString
public class MonsterInstanceController {
    private static final String NOT_FOUND_ROOM = "Room not found with hash ";
    private static final String NOT_FOUND_INSTANCE = "Monster instance not found with id ";
    private static final String NOT_FOUND_MONSTER = "Monster not found with name ";

    @Autowired
    private MonsterInstanceRepository monsterInstanceRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MonsterRepository monsterRepository;

    @GetMapping("/rooms/{roomHash}/monsterinstances")
    public Page<MonsterInstance> getMonsterInstances(@PathVariable String roomHash, Pageable pageable) {


        return monsterInstanceRepository.findByRoomHash(roomHash, pageable);
    }

    @GetMapping("/rooms/{roomHash}/monsterinstances/{monsterId}")
    public MonsterInstance getMonster(@PathVariable String roomHash, @PathVariable Long monsterId) {
        if (!roomRepository.existsByHash(roomHash)) {
            throw new ResourceNotFoundException(NOT_FOUND_ROOM + roomHash);
        }

        return monsterInstanceRepository.findById(monsterId)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_INSTANCE + monsterId));
    }

    @PostMapping("/rooms/{roomHash}/monsterinstances")
    public MonsterInstance createMonsterInstance(@PathVariable String roomHash,
                                                 @Valid @RequestBody MonsterInstance monsterInstanceRequest) {
        return roomRepository.findByHash(roomHash).map(room ->
            monsterRepository.findByName(monsterInstanceRequest.getName()).map(monster -> {
                monsterInstanceRequest.setRoom(room);
                monsterInstanceRequest.setMonster(monster);
                return monsterInstanceRepository.save(monsterInstanceRequest);
            }).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MONSTER + monsterInstanceRequest.getName()))
        ).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_ROOM + roomHash));
    }

    @PutMapping("/rooms/{roomHash}/monsterinstances/{monsterId}")
    public MonsterInstance updateMonster(@PathVariable String roomHash, @PathVariable Long monsterId,
                                 @Valid @RequestBody MonsterInstance monsterInstanceRequest) {
        if (!roomRepository.existsByHash(roomHash)) {
            throw new ResourceNotFoundException(NOT_FOUND_ROOM + roomHash);
        }

        if (!monsterRepository.existsByName(monsterInstanceRequest.getMonster().getName())) {
            throw new ResourceNotFoundException(NOT_FOUND_MONSTER + monsterInstanceRequest.getMonster().getName());
        }

        return monsterInstanceRepository.findById(monsterId).map(monsterInstance -> {
            monsterInstance.setCurrentHealth(monsterInstanceRequest.getCurrentHealth());
            return monsterInstanceRepository.save(monsterInstance);
        }).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_INSTANCE + monsterId));
    }

    @DeleteMapping("/rooms/{roomHash}/monsterinstances/{monsterId}")
    public ResponseEntity<?> deleteMonster(@PathVariable String roomHash, @PathVariable Long monsterId) {
        return monsterInstanceRepository.findByIdAndRoomHash(monsterId, roomHash).map(monsterInstance -> {
            monsterInstanceRepository.delete(monsterInstance);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Monster instance not found with id " + monsterId));
    }
}
