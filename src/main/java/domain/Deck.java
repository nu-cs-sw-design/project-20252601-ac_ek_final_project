package domain;

import domain.Cards.Card;
import domain.Cards.ExplodingKittenCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public Deck(Deck other) {
        this.cards = new ArrayList<>(other.cards);
    }

    public int numberOfCards() {
        return cards.size();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card drawTopCard() {
        if (cards.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }
        return cards.remove(0);
    }

    public Card drawBottomCard() {
        if (cards.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }
        return cards.remove(cards.size() - 1);
    }

    public void shuffle() {
        if (cards.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }
        Collections.shuffle(cards);
    }

    public List<Card> peekTopDeck(int numCardsToSee){
        if (cards.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }
        int endIndex = Math.min(numCardsToSee, cards.size());
        return cards.subList(0, endIndex);
    }

    public void alterTopDeck(List<Card> topCards, List<Integer> newIndexes){
        if (cards.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }

        if (topCards.size() != newIndexes.size()) {
            throw new IllegalArgumentException("newIndexSize");
        }

        if (newIndexes.size() != new HashSet<>(newIndexes).size()) {
            throw new IllegalArgumentException("newIndexesUnique");
        }

        int endIndex = topCards.size();
        List<Card> reorderedCards = new ArrayList<>(Collections.nCopies(endIndex, null));

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
            cards.set(j, reorderedCards.get(j));
        }
    }

    public void moveExplodingKittensToTop() {
        if (cards.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }

        List<Card> explodingKittenCards = new ArrayList<>();
        List<Card> otherCards = new ArrayList<>();

        for (Card card : cards) {
            if (card instanceof ExplodingKittenCard) {
                explodingKittenCards.add(card);
            } else {
                otherCards.add(card);
            }
        }

        if (explodingKittenCards.size() > 9) {
            throw new IllegalArgumentException("tooManyExplodingKitten");
        }

        cards.clear();
        cards.addAll(otherCards);

        if (!isEmpty()) {
            shuffle();
        }

        cards.addAll(0, explodingKittenCards);
    }

    public void swapTopAndBottom() {
        if (cards.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }
        Collections.swap(cards, 0, cards.size() - 1);
    }

    public void insertCardAtIndex(Card card, int indexOfNewCard) {
        if (indexOfNewCard < 0 || indexOfNewCard > cards.size()) {
            throw new IndexOutOfBoundsException("indexOutOfBounds");
        }
        cards.add(indexOfNewCard, card);
    }

    public Deck copy() {
        if (cards.isEmpty()) {
            return new Deck();
        }

        Deck copiedDeck = new Deck();
        for (Card card : this.cards) {
            copiedDeck.addCard(card);
        }
        return copiedDeck;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}