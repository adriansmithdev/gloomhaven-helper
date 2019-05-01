package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.subjecttochange.ghhelper.persistence.model.orm.BaseModel;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
public class Action extends BaseModel {

    @Id
    @GeneratedValue(generator = "action_generator")
    @SequenceGenerator(name = "action_generator", sequenceName = "action_sequence")
    private Long id;

    private Boolean shuffleable;
    private Integer initiative;
    @ElementCollection(targetClass = String.class)
    private List<String> actions;

    public Action() {
        super();
    }

    public static Action create(Boolean shuffleable, Integer initiative, List<String> actions) {
        Action action = new Action();
        action.setShuffleable(shuffleable);
        action.setInitiative(initiative);
        action.setActions(actions);
        return action;
    }
}
