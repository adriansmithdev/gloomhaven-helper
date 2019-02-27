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
    //private StatusEffect[] activeStatusEffects;
    //private boolean isElite;
    //private boolean takenTurn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "monster_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Monster monster;

    public MonsterInstance() {
        super();
    }

    public static MonsterInstance create(Room room, Monster monster) {
        return MonsterInstance.create(monster.getMaxHealth(), room, monster);
    }

    public static MonsterInstance create(int currentHealth, Room room, Monster monster) {
        MonsterInstance monsterInstance = new MonsterInstance();
        monsterInstance.setRoom(room);
        monsterInstance.setMonster(monster);
        monsterInstance.setCurrentHealth(currentHealth);
        return monsterInstance;
    }

}
