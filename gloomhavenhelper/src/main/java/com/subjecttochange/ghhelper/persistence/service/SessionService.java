package com.subjecttochange.ghhelper.persistence.service;

import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Element;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.Stat;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Action;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Status;
import com.subjecttochange.ghhelper.persistence.model.responsebodies.*;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import com.subjecttochange.ghhelper.persistence.repository.StatRepository;
import com.subjecttochange.ghhelper.persistence.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SessionService {

    private final RoomRepository roomRepository;
    private final MonsterRepository monsterRepository;
    private final StatusRepository statusRepository;
    private final StatRepository statRepository;

    @Autowired
    public SessionService(RoomRepository roomRepository, MonsterRepository monsterRepository,
                             StatusRepository statusRepository, StatRepository statRepository) {
        this.roomRepository = roomRepository;
        this.monsterRepository = monsterRepository;
        this.statusRepository = statusRepository;
        this.statRepository = statRepository;
    }

    @Transactional
    public Page<SessionResponseBody> getRooms(String hash, Pageable pageable) {
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
            List<ElementResponseBody> elementResponses = new ArrayList<>();
            for (Element element : room.getElements()) {
                elementResponses.add(ElementResponseBody.create(element));
            }

            RoomResponseBody roomResponse = RoomResponseBody.create(room, elementResponses);

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
        Map<Long, MonsterResponseBody> namedMonsterBodies = new TreeMap<>();
        List<Monster> monsters = monsterRepository.findAllByLevel(room.getScenarioLevel());

        for (Monster monster : monsters) {
            MonsterResponseBody monsterResponseBody = MonsterResponseBody.create(monster);
            Action currentAction = null;

            if (room.getDecks().containsKey(monster)) {
                currentAction = room.getDecks().get(monster).getCurrentAction();
            }

            if (currentAction != null) {
                monsterResponseBody.setMonsterAction(MonsterActionResponseBody.create(currentAction));
            }

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
