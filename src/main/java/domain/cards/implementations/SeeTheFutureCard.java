package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.game.GameContext;

import java.util.List;

public class SeeTheFutureCard extends Card {
    private static final int[] COUNTS = {4, 6, 10};

    private final int peekOption;

    public enum PeekOption {
        THREE(3),
        FIVE(5);

        private final int value;

        PeekOption(int value) {
            this.value = value;
        }
    }

    public SeeTheFutureCard(PeekOption peekOption) {
        super();
        this.peekOption = peekOption.value;
    }

    @Override
    public String getName() {
        return "See The Future";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("seeTheFutureCard");

        Deck deck = context.getDeck();
        DeckManager dm = context.getDeckManager();
        List<Card> topCards = dm.peekTopDeck(deck, peekOption);
        for (int i = 0; i < topCards.size(); i++) {
            context.displayFormattedMessage("visibleCard", i, topCards.get(i).getName());
        }
        CardManager cm = context.getCardManager();
        cm.removeCardFromCurrentPlayer(context, "See The Future");
    }
}