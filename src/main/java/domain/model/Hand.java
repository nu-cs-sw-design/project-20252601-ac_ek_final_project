package domain.model;

import domain.model.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private final List<Card> cards;
    private final List<Card> visibleCards;

    public Hand() {
        this.cards = new ArrayList<>();
        this.visibleCards = new ArrayList<>();
    }

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
        this.visibleCards = new ArrayList<>();
    }

    public Hand(Hand other) {
        this.cards = new ArrayList<>(other.cards);
        this.visibleCards = new ArrayList<>(other.visibleCards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addVisibleCard(Card card) {
        visibleCards.add(card);
    }

    public Card removeCard(int index) {
        if (cards.isEmpty()) {
            throw new UnsupportedOperationException("emptyHand");
        }
        if (index < 0 || index >= cards.size()) {
            throw new IndexOutOfBoundsException("indexOutOfBounds");
        }
        Card cardToRemove = cards.get(index);
        visibleCards.remove(cardToRemove);
        return cards.remove(index);
    }

    public Card getCard(int index) {
        if (index < 0 || index >= cards.size()) {
            throw new IndexOutOfBoundsException("indexOutOfBounds");
        }
        return cards.get(index);
    }

    public int findCardIndex(String cardName) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals(cardName)) {
                return i;
            }
        }
        return -1;
    }

    public boolean hasTwoOf(String cardName) {
        int count = 0;
        for (Card card : cards) {
            if (card.getName().equals(cardName)) {
                count++;
            }
        }
        return count >= 2;
    }

    public void shuffle() {
        if (cards.isEmpty()) {
            throw new UnsupportedOperationException("emptyHand");
        }
        Collections.shuffle(cards);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public List<Card> getVisibleCards() {
        return new ArrayList<>(visibleCards);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }
}
