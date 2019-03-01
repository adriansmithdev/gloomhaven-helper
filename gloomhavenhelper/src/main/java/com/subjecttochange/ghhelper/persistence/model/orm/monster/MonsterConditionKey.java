package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class MonsterConditionKey implements Serializable {

    @Column(name = "monsterinstance_id")
    private Long monsterInstanceId;

    @Column(name = "condition_id")
    private Long conditionId;

}
