package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.game.GameContext;

public class SwapTopAndBottomCard extends Card {
    private static final int[] COUNTS = {2, 3, 4};

    public SwapTopAndBottomCard() {
        super();
    }

    @Override
    public String getName() {
        return "Swap Top And Bottom";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("swapTopAndBottomCard");

        Deck deck = context.getDeck();
        DeckManager dm = context.getDeckManager();
        dm.swapTopAndBottom(deck);
        context.setDeck(deck);
        CardManager cm = context.getCardManager();
        cm.removeCardFromCurrentPlayer(context, "Swap Top And Bottom");
    }
}