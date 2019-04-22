package com.subjecttochange.ghhelper.persistence.service;

import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Element;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.repository.MonsterInstanceRepository;
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
public class RoomService {

    private final RoomRepository roomRepository;
    private final MonsterInstanceRepository instanceRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, MonsterInstanceRepository instanceRepository) {
        this.roomRepository = roomRepository;
        this.instanceRepository = instanceRepository;
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
        int scenarioNum = (roomRequest.getScenarioNumber() != null ? roomRequest.getScenarioNumber() : 0);
        int scenarioLvl = (roomRequest.getScenarioLevel() != null ? roomRequest.getScenarioLevel() : 0);

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
}
