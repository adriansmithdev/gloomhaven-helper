package com.subjecttochange.ghhelper.persistence.model.monster;

import lombok.Data;

public @Data class MonsterTraits {
    private String name;
    private int normalValue;
    private int eliteValue;
    private boolean eliteOnly;
}
