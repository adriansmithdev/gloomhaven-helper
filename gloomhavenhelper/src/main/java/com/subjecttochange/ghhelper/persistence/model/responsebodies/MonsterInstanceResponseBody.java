package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.monster.MonsterInstance;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;

@Data
public class MonsterInstanceResponseBody {
    @NonNull
    private int currentHealth;

    public static MonsterInstanceResponseBody create(MonsterInstance monsterInstance) {
        return new MonsterInstanceResponseBody(
                monsterInstance.getCurrentHealth()
        );
    }
}
