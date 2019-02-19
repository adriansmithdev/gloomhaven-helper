package com.subjecttochange.ghhelper.persistence.model;

import com.subjecttochange.ghhelper.persistence.model.helpers.RoomHashGenerator;
import com.subjecttochange.ghhelper.persistence.model.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.monster.MonsterInstance;
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

    @NotBlank
    @Size(min = 3, max = 100)
    private String hash;

    private int scenarioNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "room")
    private List<MonsterInstance> monsterInstances = new ArrayList<>();

    @Transient
    private Set<String> monsterNames;

    public Room() {
        super();
    }

    public Room(@NotBlank @Size(min = 3, max = 100) String hash, int scenarioNumber) {
        super();
        this.hash = hash;
        this.scenarioNumber = scenarioNumber;
    }

    public static Room createWithRandomHash() {
        return new Room(RoomHashGenerator.newHash(), DEFAULT_SCENARIO_NUMBER);
    }

    public static Room createWithHash(String hash) {
        return new Room(hash, DEFAULT_SCENARIO_NUMBER);
    }

    public static Room createRoom(String hash, int scenarioNumber) {
        return new Room(hash, scenarioNumber);
    }

    public void buildMonsterNameList(List<Monster> monsters) {
        monsterNames = new TreeSet<>();
        monsterNames = monsters.stream().map(Monster::getName).collect(Collectors.toSet());
    }
}
