package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Data
public class StatusEffect {
    @Id
    @GeneratedValue(generator = "status_generator")
    @SequenceGenerator(
            name = "status_generator",
            sequenceName = "status_sequence",
            initialValue = 1
    )
    private Long id;
    private String name;
    private String tooltip;

    public StatusEffect() {
        super();
    }

    public static StatusEffect create(String name, String tooltip) {
        StatusEffect statusEffect = new StatusEffect();
        statusEffect.setName(name);
        statusEffect.setTooltip(tooltip);
        return statusEffect;
    }

}
