package com.subjecttochange.ghhelper.persistence.model.jsonio.requestbodies;

import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import lombok.Data;

@Data
public class MonsterInstanceRequestBody {

    private Long monsterId;
    private boolean isMonsterIdDirty;

    private int currentHealth;
    private boolean isCurrentHealthDirty;

    public MonsterInstance updateMonsterInstance(MonsterInstance monsterInstance, Monster monster, Room room) {
        if (this.hasMonsterId()) {
            monsterInstance.setMonsterId(this.getMonsterId());
        }
        if (this.hasCurrentHealth()) {
            monsterInstance.setCurrentHealth(this.getCurrentHealth());
        }

        monsterInstance.setMonster(monster);
        monsterInstance.setRoom(room);

        return monsterInstance;
    }

    public void setMonsterId(Long monsterId) {
        this.monsterId = monsterId;
        this.isMonsterIdDirty = true;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        this.isCurrentHealthDirty = true;
    }

    public boolean hasMonsterId() {
        return isMonsterIdDirty;
    }

    public boolean hasCurrentHealth() {
        return isCurrentHealthDirty;
    }
}
