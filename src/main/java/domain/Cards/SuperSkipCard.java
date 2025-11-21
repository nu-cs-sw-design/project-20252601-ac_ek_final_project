package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

public class SuperSkipCard extends Card {
    @Override
    public String getName() {
        return "Super Skip";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("superSkipCard");

        Player currPlayer = game.getCurrentPlayer();
        if (currPlayer.getNumberOfTurns() > 0) {
            game.setCurrentPlayerTurn(0);
        }

        int cardIndex = currPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);
    }
}