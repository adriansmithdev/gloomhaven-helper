package com.subjecttochange.ghhelper.persistence.model.responsebodies;

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
    private List<String> attributes;
    @NonNull
    private List<String> eliteAttributes;
    @NonNull
    private Integer level;
    @NonNull
    private Integer health;
    @NonNull
    private Integer movement;
    @NonNull
    private Integer attack;
    @NonNull
    private Integer range;
    @NonNull
    private Integer eliteHealth;
    @NonNull
    private Integer eliteMove;
    @NonNull
    private Integer eliteAttack;
    @NonNull
    private Integer eliteRange;
    @NonNull
    private List<MonsterInstanceResponseBody> monsterInstances;
    @NonNull
    private MonsterActionResponseBody monsterAction;

    public static MonsterResponseBody create(Monster monster) {
        return new MonsterResponseBody(
                monster.getId(),
                monster.getName(),
                monster.getAttributes(),
                monster.getEliteAttributes(),
                monster.getLevel(),
                monster.getHealth(),
                monster.getMovement(),
                monster.getAttack(),
                monster.getRange(),
                monster.getEliteHealth(),
                monster.getEliteMove(),
                monster.getEliteAttack(),
                monster.getEliteRange(),
                new ArrayList<>(),
                MonsterActionResponseBody.create(monster.getCurrentAction())
        );
    }
}
