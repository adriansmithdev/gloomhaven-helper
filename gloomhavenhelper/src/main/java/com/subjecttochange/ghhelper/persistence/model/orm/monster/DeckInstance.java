package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@ToString
public class DeckInstance {

    @Id
    @GeneratedValue(generator = "active_deck_generator")
    @SequenceGenerator(name = "active_deck_generator", sequenceName = "active_deck_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    @JsonIgnore
    private ActionDeck deck;

    @OneToOne
    private Action currentAction;

    public DeckInstance(){
        super();
    }

    public static DeckInstance create(ActionDeck deck){
        DeckInstance activeDeck = new DeckInstance();

        activeDeck.setDeck(deck);

        Collections.shuffle(activeDeck.getDeck().getActionDeck());

        return activeDeck;
    }

    /**
     * Draws new current action and checks for reshuffling
     */
    public void drawNewMonsterAction(ActionDeck deck) {
        List<Action> actionDeck = deck.getActionDeck();
        currentAction = actionDeck.remove(actionDeck.size());

        if(currentAction.getShuffleable()){
            reshuffleActions(deck);
        }
    }

    private void reshuffleActions(ActionDeck deck) {
        setDeck(deck);

    }
}