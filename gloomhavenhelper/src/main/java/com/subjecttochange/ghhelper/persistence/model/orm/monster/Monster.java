package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.subjecttochange.ghhelper.persistence.model.orm.BaseModel;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
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
        super();
    }

    public static Monster create(String name, int maxHealth) {
        Monster monster = new Monster();
        monster.setName(name);
        monster.setMaxHealth(maxHealth);
        return monster;
    }

}
