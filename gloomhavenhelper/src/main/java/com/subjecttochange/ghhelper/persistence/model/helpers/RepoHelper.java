package com.subjecttochange.ghhelper.persistence.model.helpers;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.Monster;
import com.subjecttochange.ghhelper.persistence.repository.MonsterInstanceRepository;
import com.subjecttochange.ghhelper.persistence.repository.MonsterRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RepoHelper {

    @Autowired
    private MonsterRepository monsterRepository0;
    private static MonsterRepository monsterRepository;
    @Autowired
    private RoomRepository roomRepository0;
    private static RoomRepository roomRepository;
    @Autowired
    private MonsterInstanceRepository monsterInstanceRepository0;
    private static MonsterInstanceRepository monsterInstanceRepository;

    @PostConstruct
    private void initStaticRepo() {
        monsterRepository = this.monsterRepository0;
        roomRepository = this.roomRepository0;
        monsterInstanceRepository = this.monsterInstanceRepository0;
    }

    public static Collection<String> findAllMonsterNames() {
        return monsterRepository
                .findAll()
                .stream()
                .map(Monster::getName)
                .collect(Collectors.toSet());
    }
}
