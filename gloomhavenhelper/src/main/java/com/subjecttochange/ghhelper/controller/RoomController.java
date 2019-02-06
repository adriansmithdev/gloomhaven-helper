package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.Room;
import com.subjecttochange.ghhelper.persistence.model.helpers.RoomHashGenerator;
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
 * Controls restful responses to room objects
 */
@RestController
@ToString
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    /**
     * Returns a list of all rooms
     * @param pageable provides pagination in json response
     * @return json list of rooms
     */
    @GetMapping("/rooms")
    public Page<Room> getRooms(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    /**
     * Retrieves a room by its hash
     * @param roomHash hash to search for
     * @return json of room
     */
    @GetMapping("/room/{roomHash}")
    public Room getRoom(@PathVariable String roomHash) {
        Room room = roomRepository.findByRoomHash(roomHash);
        if(room == null) {
            throw new ResourceNotFoundException("Room not found with id " + roomHash);
        }
        return room;
    }

    /**
     * Creates a new room
     * @return json object of created room
     */
    @PostMapping("/rooms")
    public Room createRoom() {
        Room room = new Room(newHash());
        return roomRepository.save(room);
    }

    @PostMapping("/newroom")
    public Room newRoom() {
        Room newRoom = new Room(newHash());
        return roomRepository.save(newRoom);
    }

    @PostMapping("/newroom/{scenarioNum}")
    public Room newRoom(@Valid @PathVariable int scenarioNum) {
        Room newRoom = new Room(newHash(), scenarioNum);
        return roomRepository.save(newRoom);
    }

    private String newHash() {
        String newHash = RoomHashGenerator.generateNewHash();
        while(roomRepository.existsByRoomHash(newHash)) {
            newHash = RoomHashGenerator.generateNewHash();
        }
        return newHash;
    }

    @PostMapping("/room/{roomHash}/newMonster/{monsterName}/{maxHealth}")
    public void newMonster(@PathVariable String roomHash,
                           @PathVariable String monsterName,
                           @PathVariable int maxHealth) {
        System.out.println("RoomConNewMon: " + monsterName + " "+maxHealth);
        Room room = getRoom(roomHash);
        room.addNewMonster(monsterName, maxHealth);
        roomRepository.save(room);
    }

    @PostMapping("/room/{roomHash}/scenario/{scenarioNum}")
    public void updateScenario(@PathVariable String roomHash, @PathVariable int scenarioNum) {
        Room room = getRoom(roomHash);
        room.setScenarioNumber(scenarioNum);
        roomRepository.save(room);
    }



    /**
     * Updates a room object with passed parameters
     * @param roomHash of room to update
     * @param roomRequest json parameters passed in request
     * @return updated object as response
     */
    @PutMapping("/rooms/{roomHash}")
    public Room updateRoom(@PathVariable Long roomHash, @Valid @RequestBody Room roomRequest) {
        return roomRepository.findById(roomHash)
                .map(room -> {
                    room.setRoomHash(roomRequest.getRoomHash());
                    room.setDescription(roomRequest.getDescription());
                    return roomRepository.save(room);
                }).orElseThrow(() -> new ResourceNotFoundException("Room not found with hash " + roomHash));
    }


    /**
     * Deletes the room denoted by the hash
     * @param roomHash of room to delete
     * @return status code of operation
     */
    @DeleteMapping("/rooms/{roomHash}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long roomHash) {
        return roomRepository.findById(roomHash)
                .map(room -> {
                    roomRepository.delete(room);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + roomHash));
    }
}
