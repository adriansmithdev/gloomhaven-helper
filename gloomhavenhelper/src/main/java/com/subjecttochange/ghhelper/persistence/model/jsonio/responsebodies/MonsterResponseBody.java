package com.subjecttochange.ghhelper.persistence.model.jsonio.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class MonsterResponseBody {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private int maxHealth;
    @NonNull
    private int range;
    @NonNull
    private int attack;
    @NonNull
    private int movement;
    @NonNull
    private List<MonsterInstanceResponseBody> monsterInstances;

    public static MonsterResponseBody create(Monster monster) {
        return new MonsterResponseBody(
                monster.getId(),
                monster.getName(),
                monster.getMaxHealth(),
                monster.getRange(),
                monster.getAttack(),
                monster.getMovement(),
                new ArrayList<>()
        );
    }
}
