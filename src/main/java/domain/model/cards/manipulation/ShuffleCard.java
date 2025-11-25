package domain.model.cards.manipulation;

import domain.model.Deck;
import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

public class ShuffleCard extends Card{
    private static final int[] COUNTS = {2, 4, 6};

    public ShuffleCard() {
        super(new ShuffleCardEffect());
    }

    @Override
    public String getName() {
        return "Shuffle";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    private static class ShuffleCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("shuffleCard");

            Deck deck = context.getDeck();
            deck.shuffle();
            context.setDeck(deck);

            Player currentPlayer = context.getCurrentPlayer();
            int cardIndex = currentPlayer.hasCard("Shuffle");
            context.removeCurrentPlayerCard(cardIndex);
        }
    }
}