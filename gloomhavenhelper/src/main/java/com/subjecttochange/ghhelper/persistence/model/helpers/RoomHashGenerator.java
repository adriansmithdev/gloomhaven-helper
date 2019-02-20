package com.subjecttochange.ghhelper.persistence.model.helpers;

import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class RoomHashGenerator {

    public static final int HASHLENGTH = 7;
    @Autowired
    private RoomRepository roomRepository0;
    private static RoomRepository roomRepository;

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
        return UUID.randomUUID().toString().substring(0, HASHLENGTH);
    }
}
