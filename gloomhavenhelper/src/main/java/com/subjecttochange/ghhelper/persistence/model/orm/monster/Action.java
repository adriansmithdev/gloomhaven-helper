package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.subjecttochange.ghhelper.persistence.model.orm.BaseModel;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Action extends BaseModel {

    @Id
    @GeneratedValue(generator = "monster_action_generator")
    @SequenceGenerator(name = "monster_action_generator", sequenceName = "monster_action_sequence")
    private Long id;

    private Boolean shuffleable;
    private Integer initiative;
    @ElementCollection(targetClass = String.class)
    private List<String> actionDeck;

    public Action() {
        super();
    }

    public static Action create(Boolean shuffleable, Integer initiative, List<String> actionDeck) {
        Action action = new Action();
        action.setShuffleable(shuffleable);
        action.setInitiative(initiative);
        action.setActionDeck(actionDeck);
        return action;
    }
}
