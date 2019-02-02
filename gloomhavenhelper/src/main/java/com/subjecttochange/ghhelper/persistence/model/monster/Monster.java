package com.subjecttochange.ghhelper.persistence.model.monster;

import lombok.Data;

import javax.persistence.*;

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
    //private MonsterInstances[] instances;
    //private MonsterTraits[] traits;
    //private MonsterAction[] abilityDeck;
    //private MonsterAction[] abilityDeckDiscard;
    private int maxHealth;
    private int moveRange;
    private int attackDamage;
    private int attackRange;
    private int maxHealthElite;
    private int moveRangeElite;
    private int attackDamageElite;
    private int attackRangeElite;

}
