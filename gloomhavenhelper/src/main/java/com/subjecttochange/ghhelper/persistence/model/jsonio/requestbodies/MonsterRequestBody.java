package com.subjecttochange.ghhelper.persistence.model.jsonio.requestbodies;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import lombok.Data;

@Data
public class MonsterRequestBody {
    private String name;
    private boolean isNameDirty;

    private int maxHealth;
    private boolean isMaxHealthDirty;

    private int moveRange;
    private boolean isMoveRangeDirty;

    public Monster updateMonster(Monster monster) {
        if (this.hasName()) {
            monster.setName(this.getName());
        }
        if (this.hasMaxHealth()) {
            monster.setHealth(this.getMaxHealth());
        }
        if (this.hasMoveRange()) {
            monster.setMove(this.getMoveRange());
        }

        return monster;
    }

    public void setName(String name) {
        this.name = name;
        this.isNameDirty = true;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        this.isMaxHealthDirty = true;
    }

    public void setMoveRange(int moveRange) {
        this.moveRange = moveRange;
        this.isMoveRangeDirty = true;
    }

    public boolean hasName() {
        return isNameDirty;
    }

    public boolean hasMaxHealth() {
        return isMaxHealthDirty;
    }

    public boolean hasMoveRange() {
        return isMoveRangeDirty;
    }
}
