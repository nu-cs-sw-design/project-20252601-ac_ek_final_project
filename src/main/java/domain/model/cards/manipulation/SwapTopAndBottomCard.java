package domain.model.cards.manipulation;

import domain.model.Deck;
import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

public class SwapTopAndBottomCard extends Card{
    public SwapTopAndBottomCard() {
        super(new SwapTopAndBottomCardEffect());
    }

    @Override
    public String getName() {
        return "Swap Top and Bottom";
    }

    private static class SwapTopAndBottomCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("swapTopAndBottomCard");

            Deck deck = context.getDeck();
            if (deck.isEmpty()){
                throw new UnsupportedOperationException("deckEmpty");
            } else if (deck.numberOfCards() > 1) {
                deck.swapTopAndBottom();
                context.setDeck(deck);
            }

            Player currentPlayer = context.getCurrentPlayer();
            int cardIndex = currentPlayer.hasCard("Swap Top and Bottom");
            context.removeCurrentPlayerCard(cardIndex);
        }
    }
}