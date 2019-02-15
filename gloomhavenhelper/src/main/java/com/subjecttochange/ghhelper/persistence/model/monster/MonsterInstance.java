package com.subjecttochange.ghhelper.persistence.model.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subjecttochange.ghhelper.persistence.model.BaseModel;
import com.subjecttochange.ghhelper.persistence.model.Room;
import com.subjecttochange.ghhelper.persistence.model.StatusEffect;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "monster_instance")
public class MonsterInstance extends BaseModel {
    @Id
    @GeneratedValue(generator = "monster_instance_generator")
    @SequenceGenerator(
            name = "monster_instance_generator",
            sequenceName = "monster_instance_sequence",
            initialValue = 1
    )
    private Long id;
    private int maxHealth;
    private int currentHealth;
    //private StatusEffect[] activeStatusEffects;
    //private boolean isElite;
    //private boolean takenTurn;

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

    // This is strictly for querying the monster table and does not persist
    @Transient
    private String name;

    public MonsterInstance() {
        this(66);
    }

    public MonsterInstance(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public MonsterInstance(int currentHealth, Room room, Monster monster) {
        this.currentHealth = currentHealth;
        this.room = room;
        this.monster = monster;
    }

    public void setMonster() {

    }

}
