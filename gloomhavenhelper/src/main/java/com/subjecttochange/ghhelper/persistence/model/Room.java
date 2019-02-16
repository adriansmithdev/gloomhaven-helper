package com.subjecttochange.ghhelper.persistence.model;

import com.subjecttochange.ghhelper.persistence.model.helpers.RoomHashGenerator;
import com.subjecttochange.ghhelper.persistence.model.monster.Monster;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

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
    private String hash;

    private int scenarioNumber;

    //private Scenario scenario;
    //private int rounds;
    //private ElementsActive elementsActive;
    //private Player[] players;

    //Cascade makes objects save other nested/related objects to db
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Monster> monsterGroup;
    @Transient
    private Set<String> monsterNames;


    /**
     * Creates a new room object with random hash
     */
    public Room() {
        this(RoomHashGenerator.generateNewHash(), DEFAULT_SCENARIO_NUMBER);
    }

    /**
     * Creates new room object
     * @param hash of room
     */
    public Room(@NotBlank @Size(min = 3, max = 100) String hash) {
        this(hash, DEFAULT_SCENARIO_NUMBER);
    }

    /**
     * Create new room object
     * @param hash of room
     * @param scenarioNumber for monsters
     */
    public Room(@NotBlank @Size(min = 3, max = 100) String hash, int scenarioNumber) {
        super();
        this.hash = hash;
        setScenarioNumber(scenarioNumber);
    }

    public void addNewMonster(String monsterName, int maxHealth) {
        if (monsterGroup == null) {
            this.monsterGroup = new ArrayList<>();
        }
        this.monsterGroup.add(new Monster(monsterName, maxHealth));
    }

    public void buildMonsterNameList(List<Monster> monsters) {
        monsterNames = new TreeSet<String>();
        for (Monster monster: monsters) {
            monsterNames.add(monster.getName());
        }
    }

}
