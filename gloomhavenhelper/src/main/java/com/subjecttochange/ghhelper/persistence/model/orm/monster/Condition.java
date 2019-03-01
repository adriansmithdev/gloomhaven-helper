package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Condition {
    @Id
    @GeneratedValue(generator = "condition_generator")
    @SequenceGenerator(
            name = "condition_generator",
            sequenceName = "condition_sequence",
            initialValue = 1
    )
    private Long id;
    private String name;
    private String tooltip;
    @OneToMany(mappedBy = "condition")
    @JsonIgnore
    private Set<MonsterCondition> monsterInstances = new HashSet<>();

    public Condition() {
        super();
    }

    public static Condition create(String name, String tooltip) {
        Condition condition = new Condition();
        condition.setName(name);
        condition.setTooltip(tooltip);
        return condition;
    }

}
