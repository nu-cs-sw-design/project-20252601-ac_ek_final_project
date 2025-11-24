package domain.model.cards.attack;

import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

public class AttackCard extends Card {
    private static final int[] COUNTS = {2, 3, 5};
    private static final int NUM_TURNS_TO_ADD = 2;
    private static final int SET_CURRENT_PLAYER_TURNS = 0;

    public AttackCard() {
        super(new AttackCardEffect());
    }

    @Override
    public String getName() {
        return "Attack";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    private static class AttackCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("attackCard");

            Player currentPlayer = context.getCurrentPlayer();
            int nextPlayerTurns;
            if (currentPlayer.getNumberOfTurns() == 1) {
                nextPlayerTurns = NUM_TURNS_TO_ADD;
            } else {
                nextPlayerTurns = currentPlayer.getNumberOfTurns() + NUM_TURNS_TO_ADD;
            }
            context.setCurrentPlayerTurns(SET_CURRENT_PLAYER_TURNS);
            context.setNextPlayerTurns(nextPlayerTurns);
            
            int cardIndex = currentPlayer.hasCard("Attack");
            context.removeCurrentPlayerCard(cardIndex);
        }
    }
}