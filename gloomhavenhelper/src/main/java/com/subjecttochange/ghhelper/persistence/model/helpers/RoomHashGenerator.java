package com.subjecttochange.ghhelper.persistence.model.helpers;

import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@ToString
public class RoomHashGenerator {

    public static final int HASH_LENGTH = 7;
    private final RoomRepository roomRepository0;
    private static RoomRepository roomRepository;

    @Autowired
    public RoomHashGenerator(RoomRepository roomRepository0) {
        this.roomRepository0 = roomRepository0;
    }

    @PostConstruct
    private void initStaticRepo() {
        roomRepository = this.roomRepository0;
    }

    public static String newHash() {
        String hash = getRandomHash();

        while(roomRepository.existsByHash(hash)) {
            hash = getRandomHash();
        }

        return hash;
    }

    private static String getRandomHash() {
        return UUID.randomUUID().toString().substring(0, HASH_LENGTH);
    }
}
