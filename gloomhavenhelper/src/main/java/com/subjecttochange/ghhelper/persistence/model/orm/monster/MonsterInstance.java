package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.BaseModel;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "monster_instances")
public class MonsterInstance extends BaseModel {
    @Id
    @GeneratedValue(generator = "monster_instance_generator")
    @SequenceGenerator(
            name = "monster_instance_generator",
            sequenceName = "monster_instance_sequence",
            initialValue = 1
    )
    private Long id;
    private Integer currentHealth;
    private Boolean isElite;
    @ElementCollection(targetClass=String.class)
    private Set<String> activeStatuses;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "monster_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Monster monster;
    @Transient
    private Long monsterId;
    private Integer token;

    public MonsterInstance() {
        super();
    }

    public static MonsterInstance create(Boolean isElite, Room room, Monster monster) {
        if (isElite) {
            return MonsterInstance.create(monster.getEliteHealth(), isElite, room, monster);
        } else {
            return MonsterInstance.create(monster.getHealth(), isElite, room, monster);
        }
    }

    public static MonsterInstance create(Integer currentHealth, Boolean isElite, Room room, Monster monster) {
        MonsterInstance monsterInstance = new MonsterInstance();
        monsterInstance.setCurrentHealth(currentHealth);
        monsterInstance.setIsElite(isElite);
        monsterInstance.setActiveStatuses(new HashSet<>());
        monsterInstance.setRoom(room);
        monsterInstance.setMonster(monster);
        monsterInstance.setMonsterId(monster.getId());
        return monsterInstance;
    }

    public MonsterInstance updateMonsterInstance(MonsterInstance monsterRequest) {
        if (monsterRequest.getCurrentHealth() != null) {
            setCurrentHealth(monsterRequest.getCurrentHealth());
        }
        if (monsterRequest.getIsElite() != null) {
            setIsElite(monsterRequest.getIsElite());
        }
        if (monsterRequest.getActiveStatuses() != null) {
            setActiveStatuses(monsterRequest.getActiveStatuses());
        }
        if (monsterRequest.getToken() != null) {
            setToken(monsterRequest.getToken());
        }
        return this;
    }

    @JsonProperty(required = true)
    public void setMonsterId(Long monsterId) {
        this.monsterId = monsterId;
    }

    public static Integer nextAvailableToken(List<MonsterInstance> monsterInstanceList) {
        monsterInstanceList.sort(Comparator.comparing(MonsterInstance::getToken));

        for (int i = 1; i <= monsterInstanceList.size(); i++) {
            if (i != monsterInstanceList.get(i).getToken()) {
                return i;
            }
        }

        return monsterInstanceList.size() + 1;
    }

    public static boolean isAvailableToken(List<MonsterInstance> monsterInstanceList, Integer token) {
        for (MonsterInstance instance : monsterInstanceList) {
            if (instance.getToken().equals(token)) {
                return false;
            }
        }
        return true;
    }

    public static void checkHashMatchesGiven(MonsterInstance monsterInstance, String hash, Long id) {
        if (!monsterInstance.getRoom().getHash().equals(hash)) {
            throw new ResourceNotFoundException(Errors.WRONG_HASH_FOR_ID + id);
        }
    }
}
