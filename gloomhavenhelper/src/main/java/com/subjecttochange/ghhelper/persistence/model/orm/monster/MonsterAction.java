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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "monster_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Monster monster;

    private Boolean shuffleable;
    private Integer initiative;
    @ElementCollection(targetClass=String.class)
    private Set<String> actionDeck;

    public static MonsterAction create(Monster monster, Boolean shuffleable, Integer initiative) {
        MonsterAction action = new MonsterAction();
        action.setMonster(monster);
        action.setShuffleable(shuffleable);
        action.setInitiative(initiative);
        return action;
    }
}
