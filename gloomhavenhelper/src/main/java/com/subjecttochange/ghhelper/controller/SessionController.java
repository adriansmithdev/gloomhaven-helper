package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.Stat;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Status;
import com.subjecttochange.ghhelper.persistence.model.responsebodies.MonsterInstanceResponseBody;
import com.subjecttochange.ghhelper.persistence.model.responsebodies.MonsterResponseBody;
import com.subjecttochange.ghhelper.persistence.model.responsebodies.RoomResponseBody;
import com.subjecttochange.ghhelper.persistence.model.responsebodies.SessionResponseBody;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import com.subjecttochange.ghhelper.persistence.repository.StatRepository;
import com.subjecttochange.ghhelper.persistence.repository.StatusRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@ToString
public class SessionController {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private MonsterRepository monsterRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private StatRepository statRepository;

    @GetMapping("/sessions")
    public @ResponseBody Page<SessionResponseBody>
    getRooms(@RequestParam(value = "hash", required = false) String hash , Pageable pageable) {
        Page<Room> rooms;
        if (hash == null) {
            rooms = roomRepository.findAll(pageable);
        } else {
            Room room = roomRepository.findByHash(hash).orElseThrow(() ->
                    new ResourceNotFoundException(Errors.NO_HASH_ROOM + hash));
            rooms = new PageImpl<>(Collections.singletonList(room));
        }

        List<Status> statuses = statusRepository.findAll();
        List<Stat> stats = statRepository.findAll();

        ArrayList<SessionResponseBody> sessionResponse = new ArrayList<>();

        for (Room room : rooms) {
            RoomResponseBody roomResponse = RoomResponseBody.create(room);

            sessionResponse.add(SessionResponseBody.create(
                    roomResponse,
                    new ArrayList<>(buildMonsterResponses(room)),
                    statuses,
                    stats
            ));
        }



        return new PageImpl<>(sessionResponse);
    }

    private Collection<MonsterResponseBody> buildMonsterResponses(Room room) {
        Map<Long, MonsterResponseBody> namedMonsterBodies = new HashMap<>();
        List<Monster> monsters = monsterRepository.findAll();

        for (Monster monster : monsters) {
            namedMonsterBodies.put(monster.getId(), MonsterResponseBody.create(monster));
        }

        for (MonsterInstance monsterInstance : room.getMonsterInstances()) {
            Monster monster = monsterInstance.getMonster();

            namedMonsterBodies.get(monster.getId())
                    .getMonsterInstances()
                    .add(MonsterInstanceResponseBody.create(monsterInstance));
        }

        return namedMonsterBodies.values();
    }
}
