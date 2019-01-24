package com.subjecttochange.ghhelper.persistence.model;

import com.subjecttochange.ghhelper.persistence.model.monster.Monster;
import lombok.Data;

public @Data class Room {
    private String roomID;
    private Scenario scenario;
    private int rounds;
    private Elements elements;
    private Player[] players;
    private Monster[] monsterTypes;

}
