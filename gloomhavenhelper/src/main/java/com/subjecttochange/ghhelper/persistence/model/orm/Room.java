package com.subjecttochange.ghhelper.persistence.model.orm;

import com.subjecttochange.ghhelper.persistence.model.helpers.RoomHashGenerator;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author subjecttochange
 * @version 1
 *
 * Room object which is the base container for all data
 */
@Entity
@Table(name = "rooms")
@Data
public class Room extends BaseModel {
    private static final int DEFAULT_SCENARIO_NUMBER = 0;

    //private Scenario scenario;
    //private int rounds;
    //private ElementsActive elementsActive;
    //private Player[] players;

    @Id
    @GeneratedValue(generator = "room_generator")
    @SequenceGenerator(name = "room_generator", sequenceName = "room_sequence", initialValue = 1)
    private Long id;

    private String hash;
    private Integer scenarioNumber;

    @OrderBy("created_at")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "room")
    private List<MonsterInstance> monsterInstances = new ArrayList<>();

    public Room() {
        super();
    }

    public static Room createWithRandomHash() {
        return Room.createRoom(RoomHashGenerator.newHash(), DEFAULT_SCENARIO_NUMBER);
    }

    public static Room createWithHash(String hash) {
        return Room.createRoom(hash, DEFAULT_SCENARIO_NUMBER);
    }

    public static Room createRoom(String hash, Integer scenarioNumber) {
        Room room = new Room();
        room.setHash(hash);
        room.setScenarioNumber(scenarioNumber);
        return room;
    }
}
