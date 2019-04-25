package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterAction;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class MonsterActionResponseBody {
    @NonNull
    private Long id;
    @NonNull
    private Boolean shuffleable;
    @NonNull
    private Integer initiative;
    @NonNull
    private List<String> actionDeck;

    public static MonsterActionResponseBody create(MonsterAction action) {
        return new MonsterActionResponseBody(
                action.getId(),
                action.getShuffleable(),
                action.getInitiative(),
                new ArrayList<>(action.getActionDeck())
        );
    }
}
