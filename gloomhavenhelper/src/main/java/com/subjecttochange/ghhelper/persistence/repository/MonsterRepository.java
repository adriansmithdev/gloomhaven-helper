package com.subjecttochange.ghhelper.persistence.repository;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {
    boolean existsByNameAndLevel(String name, int level);
    Optional<Monster> findByNameAndLevel(String name, int level);

}
