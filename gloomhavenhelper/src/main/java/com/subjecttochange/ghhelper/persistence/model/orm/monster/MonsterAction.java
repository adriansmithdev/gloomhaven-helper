package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class MonsterAction {

    @Id
    @GeneratedValue(generator = "monster_action_generator")
    @SequenceGenerator(name = "monster_action_generator", sequenceName = "monster_action_sequence")
    private Long id;

    private Boolean shuffleable;
    private Integer initiative;
    @ElementCollection(targetClass=String.class)
    private Set<String> actionDeck;

    public static MonsterAction create(Boolean shuffleable, Integer initiative, Set<String> actionDeck) {
        MonsterAction action = new MonsterAction();
        action.setShuffleable(shuffleable);
        action.setInitiative(initiative);
        action.setActionDeck(actionDeck);
        return action;
    }
}
