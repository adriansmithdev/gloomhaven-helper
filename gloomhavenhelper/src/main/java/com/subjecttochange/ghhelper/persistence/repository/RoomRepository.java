package com.subjecttochange.ghhelper.persistence.repository;

import com.subjecttochange.ghhelper.persistence.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Boolean existsByRoomHash(String roomHash);
}