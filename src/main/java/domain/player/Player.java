package domain.player;

import domain.cards.Card;
import domain.deck.Deck;
import domain.game.GameEngine;
import domain.game.GameContext;

import java.util.ArrayList;

public class Player {
    private int id;
    private Hand hand;
    private int numberOfTurns;
    private boolean visibility = true;
    private boolean hasNope = false;
    private boolean isAI = false;
    private PlayerController controller;

    public Player(int id, ArrayList<Card> hand) {
        this.id = id;
        this.hand = new Hand(hand);
        this.numberOfTurns = 1;
    }

    public Player(Player player) {
        this.id = player.getId();
        this.hand = new Hand(player.hand);
        this.numberOfTurns = player.getNumberOfTurns();
        this.visibility = player.getHandVisibility();
        this.hasNope = player.getHasNope();
        this.isAI = player.isAI();
        this.controller = player.getController();
    }

    public PlayerController getController() {
        return controller;
    }

    public void setController(PlayerController controller) {
        this.controller = controller;
    }

    public boolean isAI() {
        return isAI;
    }

    public void setAI(boolean isAI) {
        this.isAI = isAI;
    }

    public Hand getHandObject() {
        return hand;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Card> getHand() {
        return new ArrayList<>(hand.getCards());
    }

    public void setHand(ArrayList<Card> handCards) {
        this.hand = new Hand(handCards);
    }

    public int getNumberOfTurns() {
        return this.numberOfTurns;
    }

    public void setNumberOfTurns(int i) {
        this.numberOfTurns = i;
    }

    public boolean getHandVisibility() {
        return visibility;
    }

    public void setHandVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public Boolean getHasNope() {
        return this.hasNope;
    }

    public void setHasNope(boolean hasNope) {
        this.hasNope = hasNope;
    }
}