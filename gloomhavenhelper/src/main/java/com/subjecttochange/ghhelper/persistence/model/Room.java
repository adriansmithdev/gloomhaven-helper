package com.subjecttochange.ghhelper.persistence.model;

import com.subjecttochange.ghhelper.persistence.model.monster.Monster;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rooms")
public @Data class Room extends BaseModel {
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
    //private Elements elements;
    //private Player[] players;
    //private Monster[] monsterTypes;


    public Room() {
        this.roomHash = "";
    }

    public Room(@NotBlank @Size(min = 3, max = 100) String roomHash) {
        this.roomHash = roomHash;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomHash() {
        return roomHash;
    }

    public void setRoomHash(String roomHash) {
        this.roomHash = roomHash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
