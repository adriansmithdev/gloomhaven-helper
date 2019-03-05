package com.subjecttochange.ghhelper.persistence.repository;

import com.subjecttochange.ghhelper.persistence.model.orm.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatRepository extends JpaRepository<Stat, Long> {
}
