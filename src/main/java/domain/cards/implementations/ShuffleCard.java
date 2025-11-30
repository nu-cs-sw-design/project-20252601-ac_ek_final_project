package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.game.GameContext;

public class ShuffleCard extends Card {
    private static final int[] COUNTS = {4, 6, 10};

    public ShuffleCard() {
        super();
    }

    @Override
    public String getName() {
        return "Shuffle";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("shuffleCard");
        
        Deck deck = context.getDeck();
        deck.shuffle();
        context.setDeck(deck);
        CardManager cm = context.getCardManager();
        cm.removeCardFromCurrentPlayer(context, "Shuffle");
    }
}