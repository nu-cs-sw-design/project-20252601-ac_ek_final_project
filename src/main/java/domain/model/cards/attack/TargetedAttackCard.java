package domain.model.cards.attack;

import domain.model.Game;
import domain.model.Player;
import domain.model.cards.Card;
import ui.UI;

public class TargetedAttackCard extends Card{
    private static final int[] COUNTS = {5, 6, 8};

    @Override
    public String getName() {
        return "Targeted Attack";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("targetedAttackCard");

        Player currentPlayer = game.getCurrentPlayer();
        int targetPlayerId = ui.promptPlayer("targetPlayerId");
        if (targetPlayerId <= 0 || targetPlayerId > game.getPlayers().size()) {
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

        game.setPlayerTurns(targetPlayerId, nextPlayerTurns);
        game.setCurrentPlayerTurns(0);

        int cardIndex = currentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }
}