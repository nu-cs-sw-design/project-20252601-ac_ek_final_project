package domain;

import domain.Cards.Card;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private int id;
    private ArrayList<Card> hand;
    private ArrayList<Card> visibleHand;
    private int numberOfTurns;
    private boolean visibility = true;
    private boolean hasNope = false;

    public Player(int id, ArrayList<Card> hand) {
        this.id = id;
        this.hand = new ArrayList<>(hand);
        this.visibleHand = new ArrayList<>();
        this.numberOfTurns = 1;
    }

    public Player(Player player) {
        this.id = player.getId();
        this.hand = player.getHand();
        this.visibleHand = player.getVisibleHand();
        this.numberOfTurns = player.getNumberOfTurns();
        this.visibility = player.getHandVisibility();
    }

    public int addCard(Card card) {
        hand.add(card);
        return hand.size();
    }

    public int addVisibleCard(Card card) {
        visibleHand.add(card);
        return visibleHand.size();
    }

    public ArrayList<Card> getVisibleHand() {
        return new ArrayList<>(this.visibleHand);
    }

    public int removeCard(int index) {
        if(this.hand.isEmpty()) {
            throw new UnsupportedOperationException("emptyHand");
        }
        if(index < 0 || index >= this.hand.size()) {
            throw new IndexOutOfBoundsException("indexOutOfBounds");
        }
        Card cardToRemove = this.hand.get(index);
        this.visibleHand.remove(cardToRemove);
        this.hand.remove(index);
        return this.hand.size();
    }

    public Card chooseCard(int index) {
        if(index < 0 || index >= hand.size()) {
            throw new IndexOutOfBoundsException("indexOutOfBounds");
        }
        return hand.get(index);
    }

    public int hasCard(String card) {
        for (int i = 0; i < hand.size(); i++) {
            Card cardInHand = hand.get(i);
            if(cardInHand.getName().equals(card)) {
                return i;
            }
        }

        return -1;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Card> getHand() {
        if (!visibility) {
            return new ArrayList<>();
        }
        return new ArrayList<>(this.hand);
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = new ArrayList<>(hand);
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
        int count = 0;
        for (Card card : hand) {
            if (card.getName().equals(cardName)) {
                count++;
            }
        }
        return count >= 2;
    }

    public void shuffleHand() {
        if (hand.isEmpty()) {
            throw new UnsupportedOperationException("emptyHand");
        }
        Collections.shuffle(hand);
    }

    public boolean getHandVisibility() {
        return visibility;
    }

    public void setHandVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public Boolean isHandEmpty() {
        return this.hand.isEmpty();
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