package com.subjecttochange.ghhelper.persistence.model.monster;

import com.subjecttochange.ghhelper.persistence.model.StatusEffects;
import lombok.Data;

public @Data class MonsterInstances {
    private int id;
    private int currentHealth;
    private StatusEffects[] statusEffects;
    private boolean isElite;
    private boolean takenTurn;
}
