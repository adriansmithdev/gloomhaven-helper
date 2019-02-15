package com.subjecttochange.ghhelper.persistence.repository;

import com.subjecttochange.ghhelper.persistence.model.Room;
import com.subjecttochange.ghhelper.persistence.model.monster.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {
    boolean existsByName(String name);
    Optional<Monster> findByName(String name);

}
