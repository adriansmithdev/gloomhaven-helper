package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.jsonio.requestbodies.RoomRequestBody;
import com.subjecttochange.ghhelper.persistence.model.jsonio.responsebodies.RoomResponseBody;
import com.subjecttochange.ghhelper.persistence.model.jsonio.responsebodies.SessionResponseBody;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
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
import java.util.List;

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
    @ResponseBody
    public Page<RoomResponseBody>
    getRooms(@RequestParam(value = "hash", required = false) String hash, Pageable pageable) {
        Page<Room> rooms;
        if (hash == null) {
            rooms = roomRepository.findAll(pageable);
        } else {
            Room room = roomRepository.findByHash(hash).orElseThrow(() ->
                    new ResourceNotFoundException(NOT_FOUND + hash));
            rooms = new PageImpl<>(Collections.singletonList(room));
        }

        ArrayList<RoomResponseBody> roomResponseBodies = new ArrayList<>();

        for (Room room : rooms) {
             roomResponseBodies.add(RoomResponseBody.create(room));
        }
        return new PageImpl<>(roomResponseBodies);
    }

    /**
     * Creates a new room
     * @return json object of created room
     */
    @PostMapping("/rooms")
    @ResponseBody
    public RoomResponseBody createRoom() {
        return RoomResponseBody.create(roomRepository.save(Room.createWithRandomHash()));
    }

    /**
     * Updates a room object with passed parameters
     * @param hash of room to update
     * @param roomRequest json parameters passed in request
     * @return updated object as response
     */
    @PutMapping("/rooms")
    public RoomResponseBody updateRoom(@RequestParam(value = "hash") String hash,
                                       @Valid @RequestBody(required = false) RoomRequestBody roomRequest) {
        Room room = roomRepository.findByHash(hash).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + hash));
        roomRequest.updateRoom(room);
        roomRepository.save(room);
        return RoomResponseBody.create(room);
    }


    /**
     * Deletes the room denoted by the hash
     * @param hash of room to delete
     * @return status code of operation
     */
    @DeleteMapping("/rooms")
    public ResponseEntity<?> deleteRoom(@RequestParam(value = "hash") String hash) {
        roomRepository.delete(roomRepository.findByHash(hash).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + hash)));
        return ResponseEntity.ok().build();
    }
}
