package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.responsebodies.RoomResponseBody;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
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
 * Controls restful responses to room objects
 */
@RestController
@ToString
public class RoomController {

    private RoomRepository roomRepository;
    private ElementController elementController;

    @Autowired
    public RoomController(RoomRepository roomRepository, ElementController elementController) {
        this.roomRepository = roomRepository;
        this.elementController = elementController;
    }

    /**
     * Returns a list of all rooms
     * @param pageable provides pagination in json response
     * @return json list of rooms
     */
    @GetMapping("/rooms")
    @ResponseBody
    public Page<Room>
    getRooms(@RequestParam(value = "hash", required = false) String hash, Pageable pageable) {
        if (hash == null) {
            return roomRepository.findAll(pageable);
        } else {
            Room room = roomRepository.findByHash(hash).orElseThrow(() ->
                    new ResourceNotFoundException(Errors.NO_HASH_ROOM + hash));
            return new PageImpl<>(Collections.singletonList(room));
        }
    }

    /**
     * Creates a new room
     * @return json object of created room
     */
    @PostMapping("/rooms")
    @ResponseBody
    public Room createRoom() {
        Room room = roomRepository.save(Room.createWithRandomHash());
        elementController.createElements(room);
        return room;
    }

    /**
     * Updates a room object with passed parameters
     * @param hash of room to update
     * @param roomRequest json parameters passed in request
     * @return updated object as response
     */
    @PutMapping("/rooms")
    @ResponseBody
    public Room updateRoom(@RequestParam(value = "hash") String hash,
                                       @Valid @RequestBody(required = false) Room roomRequest) {
        Room room = roomRepository.findByHash(hash).orElseThrow(() -> new ResourceNotFoundException(Errors.NO_HASH_ROOM + hash));
        room = room.updateRoom(roomRequest);
        return roomRepository.save(room);
    }

    /**
     * Deletes the room denoted by the hash
     * @param hash of room to delete
     * @return status code of operation
     */
    @DeleteMapping("/rooms")
    public ResponseEntity<?> deleteRoom(@RequestParam(value = "hash") String hash) {
        roomRepository.delete(roomRepository.findByHash(hash)
                .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_HASH_ROOM + hash)));
        return ResponseEntity.ok().build();
    }
}
