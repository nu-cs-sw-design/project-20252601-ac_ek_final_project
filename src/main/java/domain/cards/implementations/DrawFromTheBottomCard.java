package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.game.GameContext;
import domain.player.Player;

public class DrawFromTheBottomCard extends Card {
    private static final int[] COUNTS = {6, 8, 10};

    public DrawFromTheBottomCard() {
        super();
    }

    @Override
    public String getName() {
        return "Draw From The Bottom";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("drawFromBottomCard");

        Deck deck = context.getDeck();
        Card bottomCard = deck.drawBottomCard();
        if (bottomCard instanceof ImplodingKittenCard || bottomCard instanceof ExplodingKittenCard) {
            bottomCard.executeEffect(context);
        } else {
            context.addToCurrentPlayer(bottomCard);
        }
        
        Player currPlayer = context.getCurrentPlayer();
        int currentTurns = currPlayer.getNumberOfTurns();
        if (currentTurns > 0) {
            context.setCurrentPlayerTurns(currentTurns - 1);
        }
        context.setDeck(deck);
        CardManager cm = context.getCardManager();
        cm.removeCardFromCurrentPlayer(context, "Draw From The Bottom");
    }
}