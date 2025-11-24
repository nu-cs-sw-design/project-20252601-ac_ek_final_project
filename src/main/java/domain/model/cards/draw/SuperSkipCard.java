package domain.model.cards.draw;

import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

public class SuperSkipCard extends Card {
    private static final int SET_CURRENT_PLAYER_TURNS = 0;

    public SuperSkipCard() {
        super(new SuperSkipCardEffect());
    }

    @Override
    public String getName() {
        return "Super Skip";
    }

    private static class SuperSkipCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("superSkipCard");

            Player currPlayer = context.getCurrentPlayer();
            if (currPlayer.getNumberOfTurns() > 0) {
                context.setCurrentPlayerTurns(SET_CURRENT_PLAYER_TURNS);
            }

            int cardIndex = currPlayer.hasCard("Super Skip");
            context.removeCurrentPlayerCard(cardIndex);
        }
    }
}