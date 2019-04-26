package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subjecttochange.ghhelper.persistence.model.orm.BaseModel;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

@Entity
@Data
public class MonsterActionDeck extends BaseModel {
    @Id
    @GeneratedValue(generator = "monster_action_deck_generator")
    @SequenceGenerator(name = "monster_action_deck_generator", sequenceName = "monster_action_deck_sequence")
    private Long id;

    @JsonIgnore
    @OrderBy("id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MonsterAction> actionDeck;

    @JsonIgnore
    @OrderBy("id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MonsterAction> actionDiscard;

    @OneToOne
    private MonsterAction currentAction;

    @JsonIgnore
    private String monsterName;


    public MonsterActionDeck()  { super(); }

    public static MonsterActionDeck createDeck(String monsterName, List<MonsterAction> actions){
        MonsterActionDeck deck = new MonsterActionDeck();

        deck.setActionDeck(actions);
        deck.setActionDiscard(new Stack<>());
        deck.setCurrentAction(null);
        deck.setMonsterName(monsterName);

        return deck;
    }

    /**
     * Draws new current action and checks for reshuffling
     */
    public void drawNewMonsterAction() {
        Stack<MonsterAction> deck = new Stack<>();
        deck.addAll(getActionDeck());
        Stack<MonsterAction> discard = new Stack<>();
        discard.addAll(getActionDiscard());

        if (currentAction != null) {
            discard.push(currentAction);
            if (currentAction.getShuffleable()) {
                reshuffleActions();
            }
        }
        currentAction = deck.pop();

        setActionDeck(new ArrayList<>(deck));
        setActionDiscard(new ArrayList<>(discard));
    }

    private void reshuffleActions() {
        Stack<MonsterAction> deck = new Stack<>();
        deck.addAll(getActionDeck());
        Stack<MonsterAction> discard = new Stack<>();
        discard.addAll(getActionDiscard());

        while (!discard.empty()) {
            deck.push(discard.pop());
        }
        Collections.shuffle(deck);

        setActionDeck(new ArrayList<>(deck));
        setActionDiscard(new ArrayList<>(discard));
    }

}
