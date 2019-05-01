package com.subjecttochange.ghhelper.persistence.repository;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.ActionDeck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<ActionDeck, Long> {
    ActionDeck findByMonsterName(String monsterName);
    List<ActionDeck> findAllByMonsterName(List<String> monsters);

}
