package com.subjecttochange.ghhelper.persistence.service;

import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Element;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
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

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
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
        int prevRoundNum = room.getRound();

        room = room.updateRoom(roomRequest);
        int curRoundNum = room.getRound();

        int roundDifference = Math.abs(curRoundNum - prevRoundNum);
        Element.decrementElementsByQuantity(room, roundDifference);

        return roomRepository.save(room);
    }

    @Transactional
    public ResponseEntity<?> deleteRoom(String hash) {
        roomRepository.delete(roomRepository.findByHash(hash)
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_HASH_ROOM + hash)));
        return ResponseEntity.ok().build();
    }
}
