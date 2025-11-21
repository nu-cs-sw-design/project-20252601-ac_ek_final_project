package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

public class NopeCard extends Card {
    private static final int[] COUNTS = {4, 5, 9};

    @Override
    public String getName() {
        return "Nope";
    }

    @Override
    public void playCard(Game game, UI ui) {
        Player currentPlayer = game.getCurrentPlayer();
        game.setCurrentPlayerHasNope(currentPlayer.hasTwoOf("Nope"));
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }
}
