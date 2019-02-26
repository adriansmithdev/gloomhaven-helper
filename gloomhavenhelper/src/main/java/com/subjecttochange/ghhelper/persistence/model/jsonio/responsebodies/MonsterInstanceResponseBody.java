package com.subjecttochange.ghhelper.persistence.model.jsonio.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import lombok.Data;
import lombok.NonNull;

@Data
public class MonsterInstanceResponseBody {
    @NonNull
    private Long id;
    @NonNull
    private int currentHealth;

    public static MonsterInstanceResponseBody create(MonsterInstance monsterInstance) {
        return new MonsterInstanceResponseBody(
                monsterInstance.getId(),
                monsterInstance.getCurrentHealth()
        );
    }
}
