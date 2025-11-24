package domain.model.cards.draw;

import domain.model.Game;
import domain.model.Player;
import domain.model.cards.Card;
import ui.UI;

public class SkipCard extends Card {
    private static final int[] COUNTS = {4, 6, 10};

    @Override
    public String getName() {
        return "Skip";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("skipCard");

        Player currPlayer = game.getCurrentPlayer();
        int turns = currPlayer.getNumberOfTurns();
        if (turns > 0) {
            game.setCurrentPlayerTurns(turns - 1);
        }

        int cardIndex = currPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }
}