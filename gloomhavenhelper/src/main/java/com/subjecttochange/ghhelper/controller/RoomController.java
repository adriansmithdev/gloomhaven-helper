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

/**
 * @author subjecttochange
 * @version 1
 *
 * Controls restful responses to room objects
 */
@RestController
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    /**
     * @param pageable
     * @return
     */
    @GetMapping("/rooms")
    public Page<Room> getRooms(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    /**
     * @param roomHash
     * @return
     */
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

    /**
     * @param roomHash
     * @param scenarioNum
     */
    @PostMapping("/room/{roomHash}/scenario/{scenarioNum}")
    public void updateMonster(@PathVariable String roomHash, @PathVariable int scenarioNum) {
        getRoom(roomHash).setScenarioNumber(scenarioNum);
    }

    /**
     * @return
     */
    @PostMapping("/rooms")
    public Room createRoom() {
        String hash = RoomHashGenerator.generateNewHash();
        while(roomRepository.existsByRoomHash(hash)) {
            hash = RoomHashGenerator.generateNewHash();
        }
        Room room = new Room(hash);
        return roomRepository.save(room);
    }

    /**
     * @param roomId
     * @param roomRequest
     * @return
     */
    @PutMapping("/rooms/{roomId}")
    public Room updateRoom(@PathVariable Long roomId, @Valid @RequestBody Room roomRequest) {
        return roomRepository.findById(roomId)
                .map(room -> {
                    room.setRoomHash(roomRequest.getRoomHash());
                    room.setDescription(roomRequest.getDescription());
                    return roomRepository.save(room);
                }).orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + roomId));
    }


    /**
     * @param roomId
     * @return
     */
    @DeleteMapping("/rooms/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long roomId) {
        return roomRepository.findById(roomId)
                .map(room -> {
                    roomRepository.delete(room);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + roomId));
    }

    @Override
    public String toString() {
        return "RoomController{" +
                "roomRepository=" + roomRepository +
                '}';
    }
}
