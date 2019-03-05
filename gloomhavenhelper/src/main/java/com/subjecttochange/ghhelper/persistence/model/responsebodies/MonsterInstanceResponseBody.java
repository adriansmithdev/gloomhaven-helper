package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import lombok.Data;
import lombok.NonNull;

import java.util.Set;

@Data
public class MonsterInstanceResponseBody {
    @NonNull
    private Long id;
    @NonNull
    private Integer currentHealth;
    @NonNull
    private Boolean isElite;
    @NonNull
    private Set<String> activeStatuses;
    @NonNull
    private Long monsterId;

    public static MonsterInstanceResponseBody create(MonsterInstance monsterInstance) {
        return new MonsterInstanceResponseBody(
                monsterInstance.getId(),
                monsterInstance.getCurrentHealth(),
                monsterInstance.getIsElite(),
                monsterInstance.getActiveStatuses(),
                monsterInstance.getMonster().getId()
        );
    }
}
