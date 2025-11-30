package domain.deck;

import domain.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawPile {
    private final List<Card> cards;

    public DrawPile() {
        this.cards = new ArrayList<>();
    }

    public DrawPile(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void add(int index, Card card) {
        cards.add(index, card);
    }

    public Card removeTop() {
        if (cards.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }
        return cards.remove(0);
    }

    public Card removeBottom() {
        if (cards.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }
        return cards.remove(cards.size() - 1);
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void shuffle() {
        if (cards.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }
        Collections.shuffle(cards);
    }

    public List<Card> getAll() {
        return new ArrayList<>(cards);
    }

    public Card get(int index) {
        return cards.get(index);
    }

    public void set(int index, Card card) {
        cards.set(index, card);
    }

    public void clear() {
        cards.clear();
    }

    public void addAll(List<Card> c) {
        cards.addAll(c);
    }

    public void addAll(int index, List<Card> c) {
        cards.addAll(index, c);
    }
    
    public void swap(int i, int j) {
        Collections.swap(cards, i, j);
    }
    
    public List<Card> subList(int fromIndex, int toIndex) {
        return cards.subList(fromIndex, toIndex);
    }
}