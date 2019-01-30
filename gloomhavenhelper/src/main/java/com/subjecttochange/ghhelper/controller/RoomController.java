package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.Room;
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

    @GetMapping("/room/{roomId}")
    public Room getRoom(@PathVariable Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + roomId));
    }

    @PostMapping("/newroom")
    public Room newRoom() {
        //TODO generate & check ID
        Room newRoom = new Room("test");
        return roomRepository.save(newRoom);
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room) {
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
