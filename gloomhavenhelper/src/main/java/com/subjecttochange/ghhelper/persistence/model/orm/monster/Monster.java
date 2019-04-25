package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.subjecttochange.ghhelper.persistence.model.orm.BaseModel;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Monster extends BaseModel {
    @Id
    @GeneratedValue(generator = "monster_generator")
    @SequenceGenerator(name = "monster_generator", sequenceName = "monster_sequence")
    private Long id;
    private String name;
    @ElementCollection(targetClass=String.class)
    private List<String> attributes;
    @ElementCollection(targetClass=String.class)
    private List<String> eliteAttributes;

    @JsonIgnore
    @OrderBy("id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MonsterAction> deck;

    private Integer level;
    private Integer health;
    private Integer movement;
    private Integer attack;
    private Integer range;
    private Integer eliteHealth;
    private Integer eliteMove;
    private Integer eliteAttack;
    private Integer eliteRange;

    public Monster() {
        super();
    }

    public static Monster create(String name, Integer maxHealth) {
        Monster monster = new Monster();
        monster.setName(name);
        monster.setHealth(maxHealth);
        monster.setName(name);
        monster.setAttributes(new ArrayList<>());
        monster.setEliteAttributes(new ArrayList<>());
        monster.setLevel(0);
        monster.setHealth(maxHealth);
        monster.setMovement(0);
        monster.setAttack(0);
        monster.setRange(0);
        monster.setEliteHealth(0);
        monster.setEliteMove(0);
        monster.setEliteAttack(0);
        monster.setEliteRange(0);
        return monster;
    }

    public Monster updateMonster(Monster monsterRequest) {
        if(monsterRequest.getName() != null) {
            setName(monsterRequest.getName());
        }
        if(monsterRequest.getAttributes() != null) {
            setAttributes(monsterRequest.getAttributes());
        }
        if(monsterRequest.getEliteAttributes() != null) {
            setEliteAttributes(monsterRequest.getEliteAttributes());
        }
        if(monsterRequest.getLevel() != null) {
            setLevel(monsterRequest.getLevel());
        }
        if(monsterRequest.getHealth() != null) {
            setHealth(monsterRequest.getHealth());
        }
        if(monsterRequest.getMovement() != null) {
            setMovement(monsterRequest.getMovement());
        }
        if(monsterRequest.getAttack() != null) {
            setAttack(monsterRequest.getAttack());
        }
        if(monsterRequest.getRange() != null) {
            setRange(monsterRequest.getRange());
        }
        if(monsterRequest.getEliteHealth() != null) {
            setEliteHealth(monsterRequest.getEliteHealth());
        }
        if(monsterRequest.getEliteMove() != null) {
            setEliteMove(monsterRequest.getEliteMove());
        }
        if(monsterRequest.getEliteAttack() != null) {
            setEliteAttack(monsterRequest.getEliteAttack());
        }
        if(monsterRequest.getEliteRange() != null) {
            setEliteRange(monsterRequest.getEliteRange());
        }
        return this;
    }

    public static Monster create(JsonObject levelStats, String monsterName) {
        Monster monster = new Monster();
        monster.setLevel(levelStats.get("level").getAsInt());

        monster.setName(monsterName);
        monster.setHealth(levelStats.getAsJsonObject("normal").get("health").getAsInt());
        monster.setEliteHealth(levelStats.getAsJsonObject("elite").get("health").getAsInt());

        monster.setMovement(levelStats.getAsJsonObject("normal").get("move").getAsInt());
        monster.setEliteMove(levelStats.getAsJsonObject("elite").get("move").getAsInt());

        monster.setAttack(levelStats.getAsJsonObject("normal").get("attack").getAsInt());
        monster.setEliteAttack(levelStats.getAsJsonObject("elite").get("attack").getAsInt());

        monster.setRange(levelStats.getAsJsonObject("normal").get("range").getAsInt());
        monster.setEliteRange(levelStats.getAsJsonObject("elite").get("range").getAsInt());

        monster.setAttributes(new ArrayList<>());
        monster.addAttributes(levelStats.getAsJsonObject("normal").get("attributes").getAsJsonArray());
        monster.setEliteAttributes(new ArrayList<>());
        monster.addEliteAttributes(levelStats.getAsJsonObject("elite").get("attributes").getAsJsonArray());

        return monster;
    }




    private void addAttributes(JsonArray attributesArray) {
        for (JsonElement attribute : attributesArray) {
            attributes.add(attribute.getAsString());
        }
    }

    private void addEliteAttributes(JsonArray attributesArray) {
        for (JsonElement attribute : attributesArray) {
            eliteAttributes.add(attribute.getAsString());
        }
    }

}
