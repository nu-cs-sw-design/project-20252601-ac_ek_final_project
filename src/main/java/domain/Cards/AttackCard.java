package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

public class AttackCard extends Card {
    private static final int[] COUNTS = {2, 3, 5};
    private static final int NUM_TURNS_TO_ADD = 2;
    private static final int SET_CURRENT_PLAYER_TURNS = 0;

    @Override
    public String getName() {
        return "Attack";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("attackCard");

        Player currentPlayer = game.getCurrentPlayer();
        int nextPlayerTurns;
        if (currentPlayer.getNumberOfTurns() == 1) {
            nextPlayerTurns = NUM_TURNS_TO_ADD;
        } else {
            nextPlayerTurns = currentPlayer.getNumberOfTurns() + NUM_TURNS_TO_ADD;
        }
        game.setCurrentPlayerTurn(SET_CURRENT_PLAYER_TURNS);

        int cardIndex = currentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);

        game.setNextPlayerTurns(nextPlayerTurns);
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }
}