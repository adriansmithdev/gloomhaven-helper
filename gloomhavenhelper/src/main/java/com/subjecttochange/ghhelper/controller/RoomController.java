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

    private static final String NOT_FOUND = "Room not found with hash ";

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
     * @param hash hash to search for
     * @return json of room
     */
    @GetMapping("/rooms/{hash}")
    public Room getRoom(@PathVariable String hash) {
        return roomRepository.findByHash(hash)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + hash));
    }

    /**
     * Creates a new room
     * @return json object of created room
     */
    @PostMapping("/rooms")
    public Room createRoom() {
        return roomRepository.save(new Room(newHash()));
    }

    /**
     * Updates a room object with passed parameters
     * @param hash of room to update
     * @param roomRequest json parameters passed in request
     * @return updated object as response
     */
    @PutMapping("/rooms/{hash}")
    public Room updateRoom(@PathVariable String hash, @Valid @RequestBody Room roomRequest) {
        return roomRepository.findByHash(hash).map(room -> {
                    room.setHash(roomRequest.getHash());
                    room.setScenarioNumber(roomRequest.getScenarioNumber());
                    return roomRepository.save(room);
                }).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + hash));
    }


    /**
     * Deletes the room denoted by the hash
     * @param hash of room to delete
     * @return status code of operation
     */
    @DeleteMapping("/rooms/{hash}")
    public ResponseEntity<?> deleteRoom(@PathVariable String hash) {
        return roomRepository.findByHash(hash).map(room -> {
                    roomRepository.delete(room);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + hash));
    }

    private String newHash() {
        String newHash = RoomHashGenerator.generateNewHash();
        while(roomRepository.existsByHash(newHash)) {
            newHash = RoomHashGenerator.generateNewHash();
        }
        return newHash;
    }
}
