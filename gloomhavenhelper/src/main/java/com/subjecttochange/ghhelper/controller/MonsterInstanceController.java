package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.jsonio.requestbodies.MonsterInstanceRequestBody;
import com.subjecttochange.ghhelper.persistence.model.jsonio.requestbodies.MonsterRequestBody;
import com.subjecttochange.ghhelper.persistence.model.jsonio.responsebodies.MonsterInstanceResponseBody;
import com.subjecttochange.ghhelper.persistence.model.jsonio.responsebodies.MonsterResponseBody;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.repository.MonsterInstanceRepository;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    private static final String NOT_FOUND_ROOM = "Room not found with hash ";
    private static final String NOT_FOUND_INSTANCE = "Monster instance not found with id ";
    private static final String NOT_FOUND_MONSTER = "Monster not found with id ";

    @Autowired
    private MonsterInstanceRepository monsterInstanceRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MonsterRepository monsterRepository;

    @GetMapping("/monsterinstances")
    @ResponseBody
    public Page<MonsterInstanceResponseBody>
    getMonsterInstances(@RequestParam(value = "hash") String hash,
                        @RequestParam(value = "id", required = false) Long id,
                        Pageable pageable) {
        // if no hash - deny request
        // if hash - no id - give all instances
        // if hash - id - give sepecific instance

        Page<MonsterInstance> monsterInstances;
        if (id == null) {
            monsterInstances = monsterInstanceRepository.findByRoomHash(hash, pageable);
        } else {
            MonsterInstance monsterInstance = monsterInstanceRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException(NOT_FOUND_INSTANCE + id));
            checkHashMatchesGiven(monsterInstance, hash, id);
            monsterInstances = new PageImpl<>(Collections.singletonList(monsterInstance));
        }

        ArrayList<MonsterInstanceResponseBody> monsterInstanceResponseBodies = new ArrayList<>();

        for (MonsterInstance monsterInstance : monsterInstances) {
            monsterInstanceResponseBodies.add(MonsterInstanceResponseBody.create(monsterInstance));
        }

        return new PageImpl<>(monsterInstanceResponseBodies);
    }

    @PostMapping("/monsterinstances")
    public MonsterInstanceResponseBody createMonsterInstance(@RequestParam(value = "hash") String hash,
                                                 @Valid @RequestBody(required = false) MonsterInstanceRequestBody monsterInstanceRequest) {
        RoomMonsterComposite roomMonsterComposite = assembleMonsterInstanceDependencies(monsterInstanceRequest, hash);
        MonsterInstance monsterInstance = MonsterInstance.create(
                roomMonsterComposite.getRoom(),
                roomMonsterComposite.getMonster()
        );
        monsterInstanceRequest.updateMonsterInstance(monsterInstance, roomMonsterComposite.getMonster());
        monsterInstance = monsterInstanceRepository.save(monsterInstance);
        return MonsterInstanceResponseBody.create(monsterInstance);
    }

    @PutMapping("/monsterinstances")
    public MonsterInstanceResponseBody updateMonsterInstance(
            @RequestParam(value = "hash") String hash
            @Valid @RequestBody(required = false) MonsterInstanceRequestBody monsterInstanceRequest) {
        RoomMonsterComposite roomMonsterComposite = assembleMonsterInstanceDependencies(monsterInstanceRequest, hash);
        MonsterInstance monsterInstance = monsterInstanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_INSTANCE + id));

        checkHashMatchesGiven(monsterInstance, hash, id);

        monsterInstanceRequest.updateMonsterInstance(
                monsterInstance,
                roomMonsterComposite.getMonster()
        );
        monsterInstance = monsterInstanceRepository.save(monsterInstance);
        return MonsterInstanceResponseBody.create(monsterInstance);
    }

    @DeleteMapping("/monsterinstances")
    public ResponseEntity<?> deleteMonsterInstance(@RequestParam(value = "hash") String hash,
                                                   @RequestParam(value = "id") Long id) {
        MonsterInstance monsterInstance = monsterInstanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MONSTER + id));
        checkHashMatchesGiven(monsterInstance, hash, id);

        monsterInstanceRepository.delete(monsterInstance);
        return ResponseEntity.ok().build();
    }

    private void checkHashMatchesGiven(MonsterInstance monsterInstance, String hash, Long id) {
        if (!monsterInstance.getRoom().getHash().equals(hash)) {
            throw new ResourceNotFoundException("Wrong hash for id " + id);
        }
    }

    private RoomMonsterComposite assembleMonsterInstanceDependencies(MonsterInstanceRequestBody monsterInstanceRequest, String hash) {
        if (!monsterInstanceRequest.hasMonsterId()) {
            throw new ResourceNotFoundException("monsterId is required!");
        }
        Room room = roomRepository.findByHash(hash).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_ROOM + hash));
        Monster monster = monsterRepository.findById((long)monsterInstanceRequest.getMonsterId())
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MONSTER + monsterInstanceRequest.getMonsterId()));

        return new RoomMonsterComposite(room, monster);
    }

    @Data
    private class RoomMonsterComposite {
        @NonNull
        private Room room;
        @NonNull
        private Monster monster;
    }
}
