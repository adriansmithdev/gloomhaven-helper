package com.subjecttochange.ghhelper.persistence.model.monster;

import com.subjecttochange.ghhelper.persistence.model.StatusEffect;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class MonsterInstance {
    @Id
    @GeneratedValue(generator = "monster_instance_generator")
    @SequenceGenerator(
            name = "monster_instance_generator",
            sequenceName = "monster_instance_sequence",
            initialValue = 1
    )
    private Long id;
    private int maxHealth;
    private int currentHealth;
    //private StatusEffect[] activeStatusEffects;
    //private boolean isElite;
    //private boolean takenTurn;

    public MonsterInstance() {

    }

    public MonsterInstance(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }
}
