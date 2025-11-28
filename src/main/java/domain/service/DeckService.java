package domain.service;

import domain.factory.DeckFactory;
import domain.model.Deck;
import domain.model.ExpansionPack;
import domain.model.cards.Card;

import java.util.List;
import java.util.Set;

public class DeckService {
    private Deck deck;
    private final DeckFactory deckFactory;

    public DeckService(DeckFactory deckFactory) {
        this.deckFactory = deckFactory;
    }

    public void initializeDeck(int numberOfPlayers, Set<ExpansionPack> expansionPacks) {
        this.deck = deckFactory.createDeck(numberOfPlayers, expansionPacks);
        this.deck.shuffle();
    }

    public void addRemainingCards(int numberOfPlayers, Set<ExpansionPack> expansionPacks) {
        deckFactory.addRemainingCards(this.deck, numberOfPlayers, expansionPacks);
    }

    public Deck getDeck() {
        return deck.copy();
    }

    public void setDeck(Deck deck) {
        this.deck = new Deck(deck);
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public Card drawTopCard() {
        return deck.drawTopCard();
    }
    
    public Deck getDeckForInitialization() {
        return deck;
    }

    public int getDeckSize() {
        return deck.numberOfCards();
    }
}
