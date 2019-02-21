package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.subjecttochange.ghhelper.persistence.model.orm.BaseModel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Monster extends BaseModel {
    @Id
    @GeneratedValue(generator = "monster_generator")
    @SequenceGenerator(
            name = "monster_generator",
            sequenceName = "monster_sequence",
            initialValue = 1
    )
    private Long id;
    private String name;
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
        this("", 0);
    }

    public Monster(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
    }

    public String getName() {
        return this.name;
    }


}
