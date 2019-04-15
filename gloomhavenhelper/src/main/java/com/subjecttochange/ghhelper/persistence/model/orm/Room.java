package com.subjecttochange.ghhelper.persistence.model.orm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subjecttochange.ghhelper.persistence.model.helpers.RoomHashGenerator;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    public static final int DEFAULT_SCENARIO_NUMBER = 0;
    public static final int DEFAULT_ROUND_NUMBER = 0;
    public static final int DEFAULT_SCENARIO_LEVEL = 0;

    @Id
    @GeneratedValue(generator = "room_generator")
    @SequenceGenerator(name = "room_generator", sequenceName = "room_sequence")
    private Long id;

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

    public Room() {
        super();
    }

    public static Room createWithRandomHash() {
        return Room.createRoom(RoomHashGenerator.newHash(), DEFAULT_SCENARIO_NUMBER, DEFAULT_SCENARIO_LEVEL, DEFAULT_ROUND_NUMBER);
    }

    public static Room createWithHash(String hash) {
        return Room.createRoom(hash, DEFAULT_SCENARIO_NUMBER, DEFAULT_SCENARIO_LEVEL, DEFAULT_ROUND_NUMBER);
    }

    public static Room create(Integer scenarioNumber, Integer scenarioLevel) {
        return Room.createRoom(RoomHashGenerator.newHash(), scenarioNumber, scenarioLevel, DEFAULT_ROUND_NUMBER);
    }

    public static Room createRoom(String hash, Integer scenarioNumber, Integer scenarioLevel, Integer round) {
        Room room = new Room();
        room.setHash(hash);
        room.setScenarioNumber(scenarioNumber);
        room.setScenarioLevel(scenarioLevel);
        room.setRound(round);
        return room;
    }

    public Room updateRoom(Room roomRequest){
        if (roomRequest.getHash() != null) {
            setHash(roomRequest.getHash());
        }
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

    @Override
    public String toString(){
        return "Room: " + getHash();
    }
}
