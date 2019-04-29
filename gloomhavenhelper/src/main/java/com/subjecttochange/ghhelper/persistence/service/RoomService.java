package com.subjecttochange.ghhelper.persistence.service;

import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Element;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterActionDeck;
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

import java.util.*;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final MonsterInstanceRepository instanceRepository;
    private final DeckService deckService;

    @Autowired
    public RoomService(RoomRepository roomRepository, MonsterInstanceRepository instanceRepository, DeckService deckService) {
        this.roomRepository = roomRepository;
        this.instanceRepository = instanceRepository;
        this.deckService = deckService;
    }

    @Transactional
    public Page<Room> getRooms(String hash, Pageable pageable) {
        if (hash == null) {
            return roomRepository.findAll(pageable);
        } else {
            Room room = roomRepository.findByHash(hash).orElseThrow(() ->
                    new ResourceNotFoundException(Errors.NO_HASH_ROOM + hash));
            return new PageImpl<>(Collections.singletonList(room));
        }
    }

    @Transactional
    public Room createRoom(Room roomRequest) {
        int scenarioNum = (roomRequest.getScenarioNumber() != null ? roomRequest.getScenarioNumber() : Room.DEFAULT_SCENARIO_NUMBER);
        int scenarioLvl = (roomRequest.getScenarioLevel() != null ? roomRequest.getScenarioLevel() : Room.DEFAULT_SCENARIO_LEVEL);

        Room room = roomRepository.save(Room.create(scenarioNum, scenarioLvl));
        room.setElements(Element.createElementsForRoom(0, room));
        room = roomRepository.save(room);
        return room;
    }

    @Transactional
    public Room updateRoom(String hash, Room roomRequest) {
        Room room = roomRepository.findByHash(hash).orElseThrow(() -> new ResourceNotFoundException(Errors.NO_HASH_ROOM + hash));

        if (!isRoundEqual(roomRequest, room)) {
            Element.decrementElementsByQuantity(room, Math.abs(room.getRound() - roomRequest.getRound()));
            handleStatusEffects(room);
            room.drawMonsterAction();
        }

        if (!isScenarioLevelEqual(roomRequest, room)) {
            instanceRepository.removeAllByRoomHash(room.getHash());
        }

        room = room.updateRoom(roomRequest);
        return roomRepository.save(room);
    }

    @Transactional
    public ResponseEntity<?> deleteRoom(String hash) {
        roomRepository.delete(roomRepository.findByHash(hash)
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_HASH_ROOM + hash)));
        return ResponseEntity.ok().build();
    }

    private boolean isRoundEqual(Room request, Room stored) {
        return request.getRound() != null && request.getRound().equals(stored.getRound());
    }

    private boolean isScenarioLevelEqual(Room request, Room stored) {
        return request.getScenarioLevel() != null && request.getScenarioLevel().equals(stored.getScenarioLevel());
    }

    private void handleStatusEffects(Room room) {
        List<MonsterInstance> instances = room.getMonsterInstances();
        for (MonsterInstance instance: instances) {
            instance.removeRoundStatusEffects();
        }
    }
}
