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
    private static final int DEFAULT_SCENARIO_NUMBER = 0;
    private static final int DEFAULT_ROUND_NUMBER = 0;

    @Id
    @GeneratedValue(generator = "room_generator")
    @SequenceGenerator(name = "room_generator", sequenceName = "room_sequence")
    private Long id;

    private String hash;
    private Integer scenario;
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
        return Room.createRoom(RoomHashGenerator.newHash(), DEFAULT_SCENARIO_NUMBER, DEFAULT_ROUND_NUMBER);
    }

    public static Room createWithHash(String hash) {
        return Room.createRoom(hash, DEFAULT_SCENARIO_NUMBER, DEFAULT_ROUND_NUMBER);
    }

    public static Room createRoom(String hash, Integer scenario, Integer round) {
        Room room = new Room();
        room.setHash(hash);
        room.setScenario(scenario);
        room.setRound(round);
        return room;
    }

    public Room updateRoom(Room roomRequest){
        if (roomRequest.getHash() != null) {
            setHash(roomRequest.getHash());
        }
        if (roomRequest.getScenario() != null) {
            setScenario(roomRequest.getScenario());
        }
        if(roomRequest.getRound() != null){
            setRound(roomRequest.getRound());
        }
        return this;
    }
}
