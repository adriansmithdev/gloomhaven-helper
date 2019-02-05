package com.subjecttochange.ghhelper.persistence.model.monster;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public @Data class Monster {
    @Id
    @GeneratedValue(generator = "monster_generator")
    @SequenceGenerator(
            name = "monster_generator",
            sequenceName = "monster_sequence",
            initialValue = 1
    )
    private Long id;
    private String name;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<MonsterInstance> instances;
    //private MonsterTraits[] traits;
    //private MonsterAction[] abilityDeck;
    //private MonsterAction[] abilityDeckDiscard;
    private int maxHealth;
    private int moveRange;
    //private int attackDamage;
    //private int attackRange;
    //private int maxHealthElite;
    //private int moveRangeElite;
    //private int attackDamageElite;
    //private int attackRangeElite;

    public Monster() {

    }

    public Monster(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.instances = new ArrayList<>();
        this.instances.add(new MonsterInstance(maxHealth));  //TODO update/remove once instances fully implemented
        System.out.println("Monster: instances: " +instances.toString());
    }
}
