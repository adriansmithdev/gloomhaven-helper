package com.subjecttochange.ghhelper.persistence.model.orm;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Data
public class Stat extends BaseModel {
    @Id
    @GeneratedValue(generator = "stat_generator")
    @SequenceGenerator(
            name = "stat_generator",
            sequenceName = "stat_sequence",
            initialValue = 1
    )
    private Long id;
    private String name;
    private String tooltip;

    public Stat() {
        super();
    }

    public static Stat create(String name, String tooltip) {
        Stat stat = new Stat();
        stat.setName(name);
        stat.setTooltip(tooltip);
        return stat;
    }

}