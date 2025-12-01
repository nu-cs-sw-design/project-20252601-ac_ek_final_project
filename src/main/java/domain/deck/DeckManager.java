package domain.deck;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import domain.cards.Card;
import domain.cards.implementations.ExplodingKittenCard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeckManager {
    private final DeckCreator deckCreator;

    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2", 
        justification = "DeckCreator is intended to be shared; copying not practical")
    public DeckManager(DeckCreator deckCreator) {
        this.deckCreator = deckCreator;
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
        value = "EI_EXPOSE_REP2", 
        justification = "DeckCreator is intended to be shared; copying not practical")
    public DeckManager(DeckManager other) {
        this.deckCreator = other == null ? null : other.deckCreator;
    }

    public Deck initializeDeck(int numberOfPlayers, Set<String> expansionIds) {
        Deck deck = deckCreator.createDeck(numberOfPlayers, expansionIds);
        deck.shuffle();
        return deck;
    }

    public void addRemainingCards(Deck deck, int numberOfPlayers, Set<String> expansionIds) {
        deckCreator.addRemainingCards(deck, numberOfPlayers, expansionIds);
    }

    public Card drawTopCard(Deck deck) {
        return deck.drawTopCard();
    }
    
    public int getDeckSize(Deck deck) {
        return deck.numberOfCards();
    }

    public List<Card> peekTopDeck(Deck deck, int numCardsToSee) {
        DrawPile drawPile = deck.getDrawPile();
        if (drawPile.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }
        int endIndex = Math.min(numCardsToSee, drawPile.size());
        return drawPile.subList(0, endIndex);
    }

    public void alterTopDeck(Deck deck, List<Card> topCards, List<Integer> newIndexes) {
        DrawPile drawPile = deck.getDrawPile();
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

    public void moveExplodingKittensToTop(Deck deck) {
        DrawPile drawPile = deck.getDrawPile();
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

        if (!deck.isEmpty()) {
            deck.shuffle();
        }

        drawPile.addAll(0, explodingKittenCards);
    }

    public void swapTopAndBottom(Deck deck) {
        DrawPile drawPile = deck.getDrawPile();
        if (drawPile.isEmpty()) {
            throw new UnsupportedOperationException("deckEmpty");
        }
        drawPile.swap(0, drawPile.size() - 1);
    }

    public void insertCardAtIndex(Deck deck, Card card, int indexOfNewCard) {
        DrawPile drawPile = deck.getDrawPile();
        if (indexOfNewCard < 0 || indexOfNewCard > drawPile.size()) {
            throw new IndexOutOfBoundsException("indexOutOfBounds");
        }
        drawPile.add(indexOfNewCard, card);
    }

}