package domain.model.cards.draw;

import domain.model.Game;
import domain.model.Player;
import domain.model.cards.Card;
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
            game.setCurrentPlayerTurns(0);
        }

        int cardIndex = currPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);
    }
}