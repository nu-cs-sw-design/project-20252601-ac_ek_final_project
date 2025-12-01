package domain.cards.expansions;

import domain.deck.Deck;

public interface ExpansionStrategy {
    String getId();
    String getDisplayName();
    int getSelectionNumber();
    void addCardsToDeck(Deck deck, int playerCount);
    default void addPostDealCards(Deck deck, int playerCount) {
    }
    default int getMaxPlayers() {
        return 5;
    }
    default boolean isBaseGameModifier() {
        return false;
    }
    default int getAdditionalExplodingKittens() {
        return 0;
    }
    default int getAdditionalDefuseCards(int playerCount) {
        return 0;
    }
}