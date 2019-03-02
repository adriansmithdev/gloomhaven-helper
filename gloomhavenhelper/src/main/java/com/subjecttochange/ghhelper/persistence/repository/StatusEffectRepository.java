package com.subjecttochange.ghhelper.persistence.repository;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterCondition;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterConditionKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusEffectRepository extends JpaRepository<MonsterCondition, MonsterConditionKey> {
}
