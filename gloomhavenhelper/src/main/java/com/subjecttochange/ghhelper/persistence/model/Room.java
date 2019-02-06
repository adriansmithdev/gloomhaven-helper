package com.subjecttochange.ghhelper.persistence.model;

import com.subjecttochange.ghhelper.persistence.model.helpers.RoomHashGenerator;
import com.subjecttochange.ghhelper.persistence.model.monster.Monster;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @Id
    @GeneratedValue(generator = "room_generator")
    @SequenceGenerator(
            name = "room_generator",
            sequenceName = "room_sequence",
            initialValue = 1
    )
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String roomHash;

    @Column(columnDefinition = "text")
    private String description;

    private int scenarioNumber;

    //private Scenario scenario;
    //private int rounds;
    //private ElementsActive elementsActive;
    //private Player[] players;

    //Cascade makes objects save other nested/related objects to db
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Monster> monsterGroup;


    /**
     * Creates a new room object with random hash
     */
    public Room() {
        this(RoomHashGenerator.generateNewHash(), DEFAULT_SCENARIO_NUMBER);
    }

    /**
     * Creates new room object
     * @param roomHash of room
     */
    public Room(@NotBlank @Size(min = 3, max = 100) String roomHash) {
        this(roomHash, DEFAULT_SCENARIO_NUMBER);
    }

    /**
     * Create new room object
     * @param roomHash of room
     * @param scenarioNumber for monsters
     */
    public Room(@NotBlank @Size(min = 3, max = 100) String roomHash, int scenarioNumber) {
        super();
        this.roomHash = roomHash;
        setScenarioNumber(scenarioNumber);
    }

    public void addNewMonster(String monsterName, int maxHealth) {
        if (monsterGroup == null) {
            this.monsterGroup = new ArrayList<>();
        }
        this.monsterGroup.add(new Monster(monsterName, maxHealth));
    }

}
