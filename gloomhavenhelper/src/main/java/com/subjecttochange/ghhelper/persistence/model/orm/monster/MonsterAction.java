package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Set;

@Entity
@Data
public class MonsterAction {

    private boolean shuffleable;
    private Integer initiative;
    private Set<String> actionDeck;
}
