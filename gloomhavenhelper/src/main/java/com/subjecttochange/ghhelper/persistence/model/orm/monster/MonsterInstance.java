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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data
@Entity
@Table(name = "monster_instances")
public class MonsterInstance extends BaseModel {
    @Id
    @GeneratedValue(generator = "monster_instance_generator")
    @SequenceGenerator(
            name = "monster_instance_generator",
            sequenceName = "monster_instance_sequence"
    )
    private Long id;
    private Integer currentHealth;
    private Boolean isElite;
    @ElementCollection(targetClass=String.class)
    private Set<String> activeStatuses;
    @ElementCollection(targetClass=String.class)
    private Set<String> temporaryStatuses;

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

    public void removeRoundStatusEffects() {
        System.out.println("removeRoundStatusEffects");
        for (String status : activeStatuses) {
            System.out.println("remove: " + status);
            if (validTempStatus(status)) {
                System.out.println("remove:valid: " + status);
                if (temporaryStatuses.contains(status)) {
                    temporaryStatuses.remove(status);
                } else {
                    activeStatuses.remove(status);
                }
            }
        }
    }

    private void trackStatuses(Set<String> oldStatuses, Set<String> newStatuses) {
        for (String newStatus : newStatuses) {
            System.out.println("trackStatus: " + newStatus);
            if (!oldStatuses.contains(newStatus)) {
                if (validTempStatus(newStatus)) {
                    System.out.println("tempAdded: " + newStatus);
                    temporaryStatuses.add(newStatus);
                }
            }
        }
    }

    private boolean validTempStatus(String status) {
        if (status.equals("Immobilize") ||
                status.equals("Disarm") ||
                status.equals("Stun") ||
                status.equals("Muddle") ||
                status.equals("Invisible") ||
                status.equals("Strengthen")) {
            return true;
        }
        return false;
    }

    public MonsterInstance() {
        super();
    }

    public static MonsterInstance create(Boolean isElite, Room room, Monster monster) {
        if (isElite) {
            return MonsterInstance.create(monster.getEliteHealth(), true, room, monster);
        } else {
            return MonsterInstance.create(monster.getHealth(), false, room, monster);
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
            trackStatuses(getActiveStatuses(), monsterRequest.getActiveStatuses());
            setActiveStatuses(monsterRequest.getActiveStatuses());
        }
        if (monsterRequest.getMonsterId() != null) {
            monsterRequest.setMonsterId(monsterRequest.getMonsterId());
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

    public Integer nextAvailableToken(List<MonsterInstance> monsterInstanceList) {
        Set<Integer> tokensGiven = new TreeSet<>();
        for (MonsterInstance monsterInstance : monsterInstanceList) {
            if (monsterInstance.getMonster().equals(this.getMonster())) {
                tokensGiven.add(monsterInstance.getToken());
            }
        }

        for (int i = 0; i < tokensGiven.size(); i++) {
            if (!tokensGiven.contains(i + 1)) {
                return i + 1;
            }
        }

        return tokensGiven.size() + 1;
    }

    public static void checkHashMatchesGiven(MonsterInstance monsterInstance, String hash, Long id) {
        if (!monsterInstance.getRoom().getHash().equals(hash)) {
            throw new ResourceNotFoundException(Errors.WRONG_HASH_FOR_ID + id);
        }
    }
}
