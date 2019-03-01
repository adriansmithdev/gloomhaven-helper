package com.subjecttochange.ghhelper.persistence.repository;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusEffectRepository extends JpaRepository<MonsterCondition, Long> {
}
