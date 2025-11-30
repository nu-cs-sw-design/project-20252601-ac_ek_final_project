package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.game.GameContext;

import java.util.ArrayList;
import java.util.List;

public class AlterTheFutureCard extends Card {
    private static final int[] COUNTS = {3, 4, 5};
    private final int peekOption;

    public enum PeekOption {
        THREE(3),
        FIVE(5);

        private final int value;

        PeekOption(int value) {
            this.value = value;
        }
    }

    public AlterTheFutureCard(PeekOption peekOption) {
        super();
        this.peekOption = peekOption.value;
    }

    @Override
    public String getName() {
        return "Alter The Future";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("alterTheFutureCard");

        Deck deck = context.getDeck();
        DeckManager dm = context.getDeckManager();
        List<Card> cardsToAlter = new ArrayList<>(dm.peekTopDeck(deck, peekOption));
        for (int i = 0; i < cardsToAlter.size(); i++) {
            context.displayFormattedMessage("visibleCard", i, cardsToAlter.get(i).getName());
        }

        CardManager cm = context.getCardManager();
        List<Integer> indexes = cm.collectUniqueIndexes(context, cardsToAlter.size());
        dm.alterTopDeck(deck, cardsToAlter, indexes);
        context.setDeck(deck);
        cm.removeCardFromCurrentPlayer(context, "Alter The Future");
    }
}