package com.subjecttochange.ghhelper.persistence.repository;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterActionDeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<MonsterActionDeck, Long> {
    MonsterActionDeck findByMonsterName(String monsterName);
    List<MonsterActionDeck> findAllByMonsterName(List<String> monsters);
}
