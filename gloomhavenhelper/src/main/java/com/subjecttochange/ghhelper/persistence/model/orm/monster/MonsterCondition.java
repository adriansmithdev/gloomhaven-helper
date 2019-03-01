package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MonsterCondition {

    @EmbeddedId
    private MonsterConditionKey id;

    @Transient
    private Long monsterInstanceId;
    @Transient
    private Long conditionId;

    @ManyToOne
    @MapsId("monsterinstance_id")
    @JoinColumn(name = "monsterinstance_id")
    @JsonIgnore
    private MonsterInstance instance;

    @ManyToOne
    @MapsId("condition_id")
    @JoinColumn(name = "condition_id")
    @JsonIgnore
    private Condition condition;

    @JsonProperty(required = true)
    public void setMonsterInstanceId(Long monsterInstanceId) {
        this.monsterInstanceId = monsterInstanceId;
    }

    @JsonProperty(required = true)
    public void setConditionId(Long conditionId) {
        this.conditionId = conditionId;
    }
}
