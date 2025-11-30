package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;

public class TargetedAttackCard extends Card {
    private static final int[] COUNTS = {5, 6, 8};
    private static final int TURNS_TO_ADD = 2;
    private static final int END_TURN = 0;

    public TargetedAttackCard() {
        super();
    }

    @Override
    public String getName() {
        return "Targeted Attack";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("targetedAttackCard");

        Player currentPlayer = context.getCurrentPlayer();
        CardManager cm = context.getCardManager();
        Player targetPlayer = cm.chooseValidTargetPlayer(context, "targetPlayerId");
        int nextPlayerTurns = cm.calculateAttackTurns(currentPlayer.getNumberOfTurns(), TURNS_TO_ADD);
        context.setCurrentPlayerTurns(END_TURN);
        cm.removeCardFromCurrentPlayer(context, "Targeted Attack");
        targetPlayer.setNumberOfTurns(nextPlayerTurns);
        context.setPlayer(targetPlayer);
        context.setNextPlayerTargetPlayer(targetPlayer);
    }
}