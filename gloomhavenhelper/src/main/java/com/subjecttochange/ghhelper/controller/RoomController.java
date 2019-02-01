package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.Room;
import com.subjecttochange.ghhelper.persistence.model.helpers.RoomHashGenerator;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public Page<Room> getRooms(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    @GetMapping("/room/{roomHash}")
    public Room getRoom(@PathVariable String roomHash) {
        Room room = roomRepository.findByRoomHash(roomHash);
        if(room == null) throw new ResourceNotFoundException("Room not found with id " + roomHash);
        return room;
    }

    @PostMapping("/newroom")
    public Room newRoom() {
        String newHash = RoomHashGenerator.generateNewHash();
        while(roomRepository.existsByRoomHash(newHash)) {
            newHash = RoomHashGenerator.generateNewHash();
        }
        Room newRoom = new Room(newHash);
        return roomRepository.save(newRoom);
    }



    @PostMapping("room/{roomHash}/scenario/{scenarioNum}")
    public void updateMonster(@PathVariable String roomHash, @PathVariable int scenarioNum) {
        getRoom(roomHash).setScenarioNumber(scenarioNum);
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room) {
        String hash = RoomHashGenerator.generateNewHash();
        while(roomRepository.existsByRoomHash(hash)) {
            hash = RoomHashGenerator.generateNewHash();
        }
        room.setRoomHash(hash);
        return roomRepository.save(room);
    }

    @PutMapping("/rooms/{roomId}")
    public Room updateRoom(@PathVariable Long roomId, @Valid @RequestBody Room roomRequest) {
        return roomRepository.findById(roomId)
                .map(room -> {
                    room.setRoomHash(roomRequest.getRoomHash());
                    room.setDescription(roomRequest.getDescription());
                    return roomRepository.save(room);
                }).orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + roomId));
    }


    @DeleteMapping("/rooms/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long roomId) {
        return roomRepository.findById(roomId)
                .map(room -> {
                    roomRepository.delete(room);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + roomId));
    }
}
