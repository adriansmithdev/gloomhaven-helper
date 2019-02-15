package com.subjecttochange.ghhelper.persistence.model.seeding;

import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.Room;
import com.subjecttochange.ghhelper.persistence.model.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.repository.MonsterInstanceRepository;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private MonsterRepository monsterRepository;
    private RoomRepository roomRepository;
    private MonsterInstanceRepository monsterInstanceRepository;

    @Autowired
    public DataLoader(MonsterRepository monsterRepository, RoomRepository roomRepository,
                      MonsterInstanceRepository monsterInstanceRepository) {
        this.monsterRepository = monsterRepository;
        this.roomRepository = roomRepository;
        this.monsterInstanceRepository = monsterInstanceRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        seedMonsterRepository();
        seedRoomRepository();
        seedMonsterInstanceRepository();
    }

    private void seedMonsterRepository() {
        if (isRepoEmpty(monsterRepository)) {
            System.out.println("SEEDING: Monsters");
            List<Monster> monsters = new ArrayList<>();
            monsters.add(new Monster("Living Bones", 10));
            monsters.add(new Monster("Dancing Bones", 1));
            monsters.add(new Monster("Dead Bones", 0));
            monsterRepository.saveAll(monsters);
        }
    }

    private void seedRoomRepository() {
        if (isRepoEmpty(roomRepository)) {
            System.out.println("SEEDING: Rooms");
            List<Room> rooms = new ArrayList<>();
            rooms.add(new Room("ABCDEF"));
            rooms.add(new Room("ZYXWVU"));
            rooms.add(new Room("OOMMOO"));
            roomRepository.saveAll(rooms);
        }
    }

    private void seedMonsterInstanceRepository() {
        if (isRepoEmpty(monsterInstanceRepository)) {
            System.out.println("SEEDING: Monster Instances");

            List<MonsterInstance> instances = new ArrayList<>();
            Monster monster = monsterRepository.findByName("Living Bones")
                    .orElseThrow(() -> new ResourceNotFoundException("Could not find monster"));

            Room room = roomRepository.findByHash("ABCDEF")
                    .orElseThrow(() -> new ResourceNotFoundException("Could not find room"));

            instances.add(new MonsterInstance( 10, room, monster));

            room = roomRepository.findByHash("OOMMOO")
                    .orElseThrow(() -> new ResourceNotFoundException("Could not find room"));

            instances.add(new MonsterInstance(5, room, monster));

            monsterInstanceRepository.saveAll(instances);
        }
    }

    private boolean isRepoEmpty(JpaRepository repository) {
        return repository.count() == 0;
    }
}
