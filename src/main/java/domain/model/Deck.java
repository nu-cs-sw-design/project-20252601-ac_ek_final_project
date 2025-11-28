package domain.model;

import domain.model.cards.Card;
import domain.model.cards.special.ExplodingKittenCard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Deck {
    private DrawPile drawPile;

    public Deck() {
        this.drawPile = new DrawPile();
    }

    public Deck(Deck other) {
        this.drawPile = new DrawPile(other.drawPile.getAll());
    }

    public int numberOfCards() {
        return drawPile.size();
    }

    public void addCard(Card card) {
        drawPile.add(card);
    }

    public Card drawTopCard() {
        return drawPile.removeTop();
    }

    public Card drawBottomCard() {
        return drawPile.removeBottom();
    }

    public void shuffle() {
        drawPile.shuffle();
    }

    public List<Card> peekTopDeck(int numCardsToSee){
        if (drawPile.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }
        int endIndex = Math.min(numCardsToSee, drawPile.size());
        return drawPile.subList(0, endIndex);
    }

    public void alterTopDeck(List<Card> topCards, List<Integer> newIndexes){
        if (drawPile.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }

        if (topCards.size() != newIndexes.size()) {
            throw new IllegalArgumentException("newIndexSize");
        }

        if (newIndexes.size() != new HashSet<>(newIndexes).size()) {
            throw new IllegalArgumentException("newIndexesUnique");
        }

        int endIndex = topCards.size();
        List<Card> reorderedCards = new ArrayList<>();
        for (int i = 0; i < endIndex; i++) {
            reorderedCards.add(null);
        }

        for (int j = 0; j < endIndex; j++) {
            int newIndex = newIndexes.get(j);
            if (newIndex < 0) {
                throw new IndexOutOfBoundsException("indexOutOfBounds");
            } else if (newIndex >= endIndex) {
                throw new IndexOutOfBoundsException("indexOutOfBounds");
            } else {
                reorderedCards.set(newIndex, topCards.get(j));
            }
        }

        for (int j = 0; j < endIndex; j++) {
            drawPile.set(j, reorderedCards.get(j));
        }
    }

    public void moveExplodingKittensToTop() {
        if (drawPile.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }

        List<Card> explodingKittenCards = new ArrayList<>();
        List<Card> otherCards = new ArrayList<>();

        for (Card card : drawPile.getAll()) {
            if (card instanceof ExplodingKittenCard) {
                explodingKittenCards.add(card);
            } else {
                otherCards.add(card);
            }
        }

        if (explodingKittenCards.size() > 9) {
            throw new IllegalArgumentException("tooManyExplodingKitten");
        }

        drawPile.clear();
        drawPile.addAll(otherCards);

        if (!isEmpty()) {
            shuffle();
        }

        drawPile.addAll(0, explodingKittenCards);
    }

    public void swapTopAndBottom() {
        if (drawPile.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }
        drawPile.swap(0, drawPile.size() - 1);
    }

    public void insertCardAtIndex(Card card, int indexOfNewCard) {
        if (indexOfNewCard < 0 || indexOfNewCard > drawPile.size()) {
            throw new IndexOutOfBoundsException("indexOutOfBounds");
        }
        drawPile.add(indexOfNewCard, card);
    }

    public Deck copy() {
        if (drawPile.isEmpty()) {
            return new Deck();
        }

        Deck copiedDeck = new Deck();
        for (Card card : this.drawPile.getAll()) {
            copiedDeck.addCard(card);
        }
        return copiedDeck;
    }

    public boolean isEmpty() {
        return drawPile.isEmpty();
    }
}