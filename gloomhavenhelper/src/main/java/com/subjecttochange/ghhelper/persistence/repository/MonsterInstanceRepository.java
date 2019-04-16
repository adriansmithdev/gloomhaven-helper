package com.subjecttochange.ghhelper.persistence.repository;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonsterInstanceRepository extends JpaRepository<MonsterInstance, Long> {
    Page<MonsterInstance> findByRoomHash(String roomHash, Pageable pageable);
    Optional<MonsterInstance> findByIdAndRoomHash(Long id, String roomHash);
    List<MonsterInstance> findAllByRoomHash(String roomHash);
    void removeAllByRoomHash(String roomHash);

}
