package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subjecttochange.ghhelper.persistence.model.orm.BaseModel;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ActionDeck extends BaseModel {
    @Id
    @GeneratedValue(generator = "monster_action_deck_generator")
    @SequenceGenerator(name = "monster_action_deck_generator", sequenceName = "monster_action_deck_sequence")
    private Long id;

    @JsonIgnore
    @OrderBy("id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Action> actionDeck;

    @JsonIgnore
    private String monsterName;


    public ActionDeck() {
        super();
    }

    public static ActionDeck createDeck(String monsterName, List<Action> actionDeck) {
        ActionDeck deck = new ActionDeck();
        deck.setActionDeck(actionDeck);
        deck.setMonsterName(monsterName);
        return deck;
    }


}
