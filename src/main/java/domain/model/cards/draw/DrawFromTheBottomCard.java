package domain.model.cards.draw;

import domain.model.Deck;
import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;
import domain.model.cards.special.ImplodingKittenCard;
import domain.model.cards.special.ExplodingKittenCard;

public class DrawFromTheBottomCard extends Card {
    private static final int[] COUNTS = {6, 8, 10};

    public DrawFromTheBottomCard() {
        super(new DrawFromTheBottomCardEffect());
    }

    @Override
    public String getName() {
        return "Draw From The Bottom";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    private static class DrawFromTheBottomCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("drawFromBottomCard");

            Deck deck = context.getDeck();
            Card bottomCard = deck.drawBottomCard();
            if (bottomCard instanceof ImplodingKittenCard || bottomCard instanceof ExplodingKittenCard) {
                bottomCard.getEffect().execute(context);
            } else {
                context.addToCurrentPlayer(bottomCard);
            }
            
            Player currPlayer = context.getCurrentPlayer();
            int currentTurns = currPlayer.getNumberOfTurns();
            if (currentTurns > 0) {
                context.setCurrentPlayerTurns(currentTurns - 1);
            }
            context.setDeck(deck);

            int cardIndex = currPlayer.hasCard("Draw From The Bottom");
            context.removeCurrentPlayerCard(cardIndex);
        }
    }
}