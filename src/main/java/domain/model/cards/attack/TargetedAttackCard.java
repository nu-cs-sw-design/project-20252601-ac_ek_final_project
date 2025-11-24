package domain.model.cards.attack;

import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

public class TargetedAttackCard extends Card{
    private static final int[] COUNTS = {5, 6, 8};
    private static final int SET_CURRENT_PLAYER_TURNS = 0;

    public TargetedAttackCard() {
        super(new TargetedAttackCardEffect());
    }

    @Override
    public String getName() {
        return "Targeted Attack";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    private static class TargetedAttackCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("targetedAttackCard");

            Player currentPlayer = context.getCurrentPlayer();
            int targetPlayerId = context.promptPlayer("targetPlayerId");
            if (targetPlayerId <= 0 || targetPlayerId > context.getPlayers().size()) {
                throw new UnsupportedOperationException("invalidPlayerID");
            }
            if (currentPlayer.getId() == targetPlayerId) {
                throw new UnsupportedOperationException("chosenSelfError");
            }

            int currentPlayerTurns = currentPlayer.getNumberOfTurns();
            int nextPlayerTurns;
            if (currentPlayerTurns == 1) {
                nextPlayerTurns = 2;
            } else {
                nextPlayerTurns = currentPlayerTurns + 2;
            }

            context.setCurrentPlayerTurns(SET_CURRENT_PLAYER_TURNS);

            int cardIndex = currentPlayer.hasCard("Targeted Attack");
            context.removeCurrentPlayerCard(cardIndex);

            Player targetPlayer = context.getPlayer(targetPlayerId);
            targetPlayer.setNumberOfTurns(nextPlayerTurns);
            context.setPlayer(targetPlayer);
            context.setNextPlayerTargetPlayer(targetPlayer);
        }
    }
}