package com.subjecttochange.ghhelper.persistence.model.monster;

import lombok.Data;

public @Data class Monster {
    private String name;
    private MonsterInstances[] instances;
    private MonsterTraits[] traits;
    private MonsterAction[] abilityDeck;
    private MonsterAction[] abilityDeckDiscard;
    private int maxHealth;
    private int moveRange;
    private int attackDamage;
    private int attackRange;
    private int maxHealthElite;
    private int moveRangeElite;
    private int attackDamageElite;
    private int attackRangeElite;

}
