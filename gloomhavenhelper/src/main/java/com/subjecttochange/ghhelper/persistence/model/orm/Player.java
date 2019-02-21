package com.subjecttochange.ghhelper.persistence.model.orm;

import lombok.Data;

public @Data class Player {
    private String playerName;
    private String characterName;
    private String characterClass;
    private int maxHealth;
    private int currentHealth;
    private int initiative;
    private int level;
    private int experience;

}
