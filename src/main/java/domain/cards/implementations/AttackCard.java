package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;

public class AttackCard extends Card {
    private static final int[] COUNTS = {2, 3, 5};
    private static final int TURNS_TO_ADD = 2;
    private static final int END_TURN = 0;

    public AttackCard() {
        super();
    }

    @Override
    public String getName() {
        return "Attack";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("attackCard");

        Player currentPlayer = context.getCurrentPlayer();
        CardManager cm = context.getCardManager();
        int nextPlayerTurns = cm.calculateAttackTurns(currentPlayer.getNumberOfTurns(), TURNS_TO_ADD);
        context.setCurrentPlayerTurns(END_TURN);
        context.setNextPlayerTurns(nextPlayerTurns);
        cm.removeCardFromCurrentPlayer(context, "Attack");
    }
}