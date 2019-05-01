package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subjecttochange.ghhelper.persistence.model.orm.BaseModel;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString
public class ActionDeck extends BaseModel {

    @Id
    @GeneratedValue(generator = "action_deck_generator")
    @SequenceGenerator(name = "action_deck_generator", sequenceName = "action_deck_sequence")
    private Long id;

    @JsonIgnore
    @OrderBy("id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Action> deck;

    @JsonIgnore
    private String monsterName;


    public ActionDeck() {
        super();
    }

    public static ActionDeck createDeck(String monsterName, List<Action> deck) {
        ActionDeck actionDeck = new ActionDeck();
        actionDeck.setDeck(deck);
        actionDeck.setMonsterName(monsterName);
        return actionDeck;
    }


}
