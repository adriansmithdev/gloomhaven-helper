package com.subjecttochange.ghhelper.persistence.model.seeding;

import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.helpers.JsonFileParser;
import com.subjecttochange.ghhelper.persistence.model.orm.Element;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.Stat;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Status;
import com.subjecttochange.ghhelper.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private MonsterRepository monsterRepository;
    private RoomRepository roomRepository;
    private MonsterInstanceRepository monsterInstanceRepository;
    private StatusRepository statusRepository;
    private StatRepository statRepository;
    private ElementRepository elementRepository;

    @Autowired
    public DataLoader(MonsterRepository monsterRepository, RoomRepository roomRepository,
                      MonsterInstanceRepository monsterInstanceRepository, StatusRepository statusRepository,
                      StatRepository statRepository, ElementRepository elementRepository) {
        this.monsterRepository = monsterRepository;
        this.roomRepository = roomRepository;
        this.monsterInstanceRepository = monsterInstanceRepository;
        this.statusRepository = statusRepository;
        this.statRepository = statRepository;
        this.elementRepository = elementRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        seedMonsterRepository();
        seedRoomRepository();
        seedStatusRepository();
        seedStatRepository();
        seedMonsterInstanceRepository();
    }

    private void seedMonsterRepository() {
        if (isRepoEmpty(monsterRepository)) {
            System.out.println("SEEDING: Monsters");
            JsonFileParser monsterSeed = new JsonFileParser("monsterseed.json");
            monsterRepository.saveAll(monsterSeed.getMonsters());
        }
    }

    private void seedRoomRepository() {
        if (isRepoEmpty(roomRepository)) {
            System.out.println("SEEDING: Rooms");
            List<Room> rooms = new ArrayList<>();
            rooms.add(Room.createWithHash("ABCDEF"));
            rooms.add(Room.createWithHash("ZYXWVU"));
            rooms.add(Room.createWithHash("OOMMOO"));
            roomRepository.saveAll(rooms);

            for (Room room : rooms) {
                elementRepository.saveAll(Element.createElementsForRoom(0, room));
            }
        }
    }

    private void seedStatusRepository() {
        if (isRepoEmpty(statusRepository)) {
            System.out.println("SEEDING: Statuses");
            List<Status> statuses = new ArrayList<>();
            statuses.add(Status.create("Poison", "+1 Attack vs figures. Heal removes poison and heal has no other effect."));
            statuses.add(Status.create("Wound", "Suffers 1 damage at the star of each turn. Heals removes and heals continues normal."));
            statuses.add(Status.create("Immobilize", "Cannot perform move abilities. Removed at end of its next turn."));
            statuses.add(Status.create("Disarm", "Cannot perform any attack abilities. Removed at end of its next turn."));
            statuses.add(Status.create("Stun", "Cannot perform any abilities/items. Must play cards like normal. Removed at end of next turn."));
            statuses.add(Status.create("Muddle", "Gains disadvantage on all attacks. Removed at end of next turn."));
            statuses.add(Status.create("Curse", "Must shuffle curse into attack modifier deck."));
            statuses.add(Status.create("Invisible", "Cannot be focused or targeted by enemy. Removed at end of next turn."));
            statuses.add(Status.create("Strengthen", "Figure gains advantage on all of its attacks. Removed at end of next turn."));
            statuses.add(Status.create("Bless", "Must shuffle bless into attack modifier deck."));
            statusRepository.saveAll(statuses);
        }
    }

    private void seedStatRepository() {
        if (isRepoEmpty(statRepository)) {
            System.out.println("SEEDING: Stats");
            List<Stat> stat = new ArrayList<>();
            stat.add(Stat.create("Attack", "The amount of damage done."));
            stat.add(Stat.create("Flying", "Fly from units."));
            stat.add(Stat.create("Heal", "Recover lost health."));
            stat.add(Stat.create("Jump", "Walk over obstacles."));
            stat.add(Stat.create("Movement", "Distance a target can move."));
            stat.add(Stat.create("Range", "The in block spaces."));
            stat.add(Stat.create("Retaliate", "Damage reflected when hurt."));
            stat.add(Stat.create("Shield", "The amount of damage absorbed."));
            statRepository.saveAll(stat);
        }
    }

    private void seedMonsterInstanceRepository() {
        if (isRepoEmpty(monsterInstanceRepository)) {
            System.out.println("SEEDING: Monster Instances");

            List<MonsterInstance> instances = new ArrayList<>();
            Monster monster = monsterRepository.findByName("Lurker")
                    .orElseThrow(() -> new ResourceNotFoundException("Could not find monster"));

            Room room = roomRepository.findByHash("ABCDEF")
                    .orElseThrow(() -> new ResourceNotFoundException("Could not find room"));

            MonsterInstance monsterInstance = MonsterInstance.create( 10, room, monster);
            Set<String> activeStatus = new HashSet<>();
            activeStatus.add("Bless");
            activeStatus.add("Curse");
            activeStatus.add("Stun");
            monsterInstance.setActiveStatuses(activeStatus);
            instances.add(monsterInstance);

            room = roomRepository.findByHash("OOMMOO")
                    .orElseThrow(() -> new ResourceNotFoundException("Could not find room"));

            instances.add(MonsterInstance.create(5, room, monster));

            monsterInstanceRepository.saveAll(instances);
        }
    }

    private boolean isRepoEmpty(JpaRepository repository) {
        return repository.count() == 0;
    }
}
