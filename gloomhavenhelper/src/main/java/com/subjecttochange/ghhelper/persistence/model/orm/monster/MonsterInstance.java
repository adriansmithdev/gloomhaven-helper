package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.BaseModel;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    public MonsterInstance() {
        super();
    }

    public static MonsterInstance create(Room room, Monster monster) {
        return MonsterInstance.create(monster.getHealth(), room, monster);
    }

    public static MonsterInstance create(Integer currentHealth, Room room, Monster monster) {
        MonsterInstance monsterInstance = new MonsterInstance();
        monsterInstance.setCurrentHealth(currentHealth);
        monsterInstance.setIsElite(false);
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
        return this;
    }

    @JsonProperty(required = true)
    public void setMonsterId(Long monsterId) {
        this.monsterId = monsterId;
    }

    public static void checkHashMatchesGiven(MonsterInstance monsterInstance, String hash, Long id) {
        if (!monsterInstance.getRoom().getHash().equals(hash)) {
            throw new ResourceNotFoundException("Wrong hash for id " + id);
        }
    }
}
