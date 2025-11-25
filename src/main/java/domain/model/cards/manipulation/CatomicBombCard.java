package domain.model.cards.manipulation;

import domain.model.Deck;
import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

public class CatomicBombCard extends Card{
    private static final int SET_NUMBER_OF_TURNS = 0;

    public CatomicBombCard() {
        super(new CatomicBombCardEffect());
    }

    @Override
    public String getName() {
        return "Catomic Bomb";
    }

    private static class CatomicBombCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("catomicBombCard");

            Deck deck = context.getDeck();
            if (deck.isEmpty()){
                throw new UnsupportedOperationException("deckEmpty");
            }

            deck.moveExplodingKittensToTop();
            context.setDeck(deck);

            Player currentPlayer = context.getCurrentPlayer();
            int cardIndex = currentPlayer.hasCard("Catomic Bomb");
            context.removeCurrentPlayerCard(cardIndex);

            currentPlayer.setNumberOfTurns(SET_NUMBER_OF_TURNS);
            context.setPlayer(currentPlayer);
        }
    }
}