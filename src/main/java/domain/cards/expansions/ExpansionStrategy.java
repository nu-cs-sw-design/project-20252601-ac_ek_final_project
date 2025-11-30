package domain.cards.expansions;

import domain.cards.Card;
import domain.deck.Deck;

import java.util.List;

public interface ExpansionStrategy {
    String getId();
    String getDisplayName();
    int getSelectionNumber();
    void addCardsToDeck(Deck deck, int playerCount);
    default void addPostDealCards(Deck deck, int playerCount) {
    }
    default int getMaxPlayers() {
        return 5; // Default base game max
    }
    default int getMinPlayers() {
        return 2;
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
    default boolean hasImplodingKitten() {
        return false;
    }
}