package com.subjecttochange.ghhelper.persistence.model;

import com.subjecttochange.ghhelper.persistence.model.monster.Monster;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Entity
@Table(name = "rooms")
public @Data class Room extends BaseModel {
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
    private ArrayList<Monster> monsterGroup;


    public Room() {
        this.roomHash = "";
        setScenarioNumber(DEFAULT_SCENARIO_NUMBER);
    }

    public Room(@NotBlank @Size(min = 3, max = 100) String roomHash) {
        this.roomHash = roomHash;
        setScenarioNumber(DEFAULT_SCENARIO_NUMBER);
    }

    public Room(@NotBlank @Size(min = 3, max = 100) String roomHash, int scenarioNumber) {
        this.roomHash = roomHash;
        setScenarioNumber(scenarioNumber);  //use setter so monster population is triggered
    }

    public void addNewMonster(String monsterName, int maxHealth) {
        if (monsterGroup == null) this.monsterGroup = new ArrayList<>();
        this.monsterGroup.add(new Monster(monsterName, maxHealth));
    }

}
