package com.subjecttochange.ghhelper.persistence.model.monster;

import com.subjecttochange.ghhelper.persistence.model.StatusEffect;
import lombok.Data;

public @Data class MonsterInstances {
    private int id;
    private int currentHealth;
    private StatusEffect[] statusEffects;
    private boolean isElite;
    private boolean takenTurn;
}
