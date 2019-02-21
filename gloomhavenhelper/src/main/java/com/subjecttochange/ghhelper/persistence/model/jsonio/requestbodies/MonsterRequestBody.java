package com.subjecttochange.ghhelper.persistence.model.jsonio.requestbodies;

import lombok.Data;

@Data
public class MonsterRequestBody {
    private String name;
    private int maxHealth;
    private int moveRange;
}
