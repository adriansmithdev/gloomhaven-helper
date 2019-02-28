package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.subjecttochange.ghhelper.persistence.model.orm.BaseModel;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private int currentHealth;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "status_active",
            joinColumns = @JoinColumn(name = "monsterinstance_id"),
            inverseJoinColumns = @JoinColumn(name = "statuseffect_id"))
    @JsonIgnore
    private Set<StatusEffect> statuses = new HashSet<>();
    private Boolean isElite = false;

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

    public static MonsterInstance create(int currentHealth, Room room, Monster monster) {
        MonsterInstance monsterInstance = new MonsterInstance();
        monsterInstance.setRoom(room);
        monsterInstance.setMonster(monster);
        monsterInstance.setCurrentHealth(currentHealth);
        monsterInstance.setMonsterId(monster.getId());
        return monsterInstance;
    }

    @JsonProperty(required = true)
    public void setMonsterId(Long monsterId) {
        this.monsterId = monsterId;
    }
}
