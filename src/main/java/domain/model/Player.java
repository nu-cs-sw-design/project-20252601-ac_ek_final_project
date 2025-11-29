package domain.model;

import domain.model.cards.Card;

import java.util.ArrayList;

public class Player {
    private int id;
    private Hand hand;
    private int numberOfTurns;
    private boolean visibility = true;
    private boolean hasNope = false;
    private boolean isAI = false;

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
    }

    public boolean isAI() {
        return isAI;
    }

    public void setAI(boolean isAI) {
        this.isAI = isAI;
    }

    public int addCard(Card card) {
        hand.addCard(card);
        return hand.size();
    }

    public int addVisibleCard(Card card) {
        hand.addVisibleCard(card);
        return hand.getVisibleCards().size();
    }

    public ArrayList<Card> getVisibleHand() {
        return new ArrayList<>(hand.getVisibleCards());
    }

    public int removeCard(int index) {
        hand.removeCard(index);
        return hand.size();
    }

    public Card chooseCard(int index) {
        return hand.getCard(index);
    }

    public int hasCard(String card) {
        return hand.findCardIndex(card);
    }

    public int getId() {
        return id;
    }

    public ArrayList<Card> getHand() {
        if (!visibility) {
            return new ArrayList<>();
        }
        return new ArrayList<>(hand.getCards());
    }

    public void setHand(ArrayList<Card> handCards) {
        this.hand = new Hand(handCards);
    }

    public boolean getIsTurnOver() {
        return this.numberOfTurns == 0;
    }

    public int getNumberOfTurns() {
        return this.numberOfTurns;
    }

    public void setNumberOfTurns(int i) {
        this.numberOfTurns = i;
    }

    public void decreaseTurnByOne() {
        if (this.getNumberOfTurns() < 1) {
            throw new UnsupportedOperationException("nonNegTurns");
        }
        this.numberOfTurns--;
    }

    public boolean hasTwoOf(String cardName) {
        return hand.hasTwoOf(cardName);
    }

    public void shuffleHand() {
        hand.shuffle();
    }

    public boolean getHandVisibility() {
        return visibility;
    }

    public void setHandVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public Boolean isHandEmpty() {
        return hand.isEmpty();
    }

    public int getHandCount() {
        return hand.size();
    }

    public Boolean getHasNope() {
        return this.hasNope;
    }

    public void setHasNope(boolean hasNope) {
        this.hasNope = hasNope;
    }
}