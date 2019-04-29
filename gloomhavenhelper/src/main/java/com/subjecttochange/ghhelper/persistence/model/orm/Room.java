package com.subjecttochange.ghhelper.persistence.model.orm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subjecttochange.ghhelper.persistence.model.helpers.RoomHashGenerator;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterActionDeck;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author subjecttochange
 * @version 1
 *
 * Room object which is the base container for all data
 */
@Entity
@Table(name = "rooms")
@Data
@ToString
public class Room extends BaseModel {
    public static final int DEFAULT_SCENARIO_NUMBER = 0;
    public static final int DEFAULT_ROUND_NUMBER = 0;
    public static final int DEFAULT_SCENARIO_LEVEL = 0;

    @Id
    @GeneratedValue(generator = "room_generator")
    @SequenceGenerator(name = "room_generator", sequenceName = "room_sequence")
    private Long id;

    @Setter(AccessLevel.NONE)
    private String hash;
    private Integer scenarioNumber;
    private Integer scenarioLevel;
    private Integer round;

    @OrderBy("created_at")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "room")
    @JsonIgnore
    private List<MonsterInstance> monsterInstances = new ArrayList<>();

    @OrderBy("id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "room")
    @JsonIgnore
    private List<Element> elements = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<Monster, MonsterActionDeck> decks = new HashMap<>();

    public Room() {
        super();
    }

    public static Room create() {
        return Room.create(DEFAULT_SCENARIO_NUMBER, DEFAULT_SCENARIO_LEVEL, DEFAULT_ROUND_NUMBER);
    }

    public static Room create(Integer scenarioNumber, Integer scenarioLevel) {
        return Room.create(scenarioNumber, scenarioLevel, DEFAULT_ROUND_NUMBER);
    }

    private static Room create(Integer scenarioNumber, Integer scenarioLevel, Integer round) {
        Room room = new Room();
        room.hash = RoomHashGenerator.newHash();
        room.setScenarioNumber(scenarioNumber);
        room.setScenarioLevel(scenarioLevel);
        room.setRound(round);
        return room;
    }

    public Room updateRoom(Room roomRequest){
        if (roomRequest.getScenarioNumber() != null) {
            setScenarioNumber(roomRequest.getScenarioNumber());
        }
        if (roomRequest.getScenarioLevel() != null) {
            setScenarioLevel(roomRequest.getScenarioLevel());
        }
        if(roomRequest.getRound() != null){
            setRound(roomRequest.getRound());
        }
        return this;
    }

    public void drawMonsterAction(){}

}
