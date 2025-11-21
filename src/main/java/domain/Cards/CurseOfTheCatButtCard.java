package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

import java.util.InputMismatchException;

public class CurseOfTheCatButtCard extends Card{
    @Override
    public String getName() {
        return "Curse of the Cat Butt";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("curseCatButt");

        Player currentPlayer = game.getCurrentPlayer();
        int chosenPlayerIndex = ui.promptPlayer("playerIDCurse");
        Player chosenPlayer = game.getPlayer(chosenPlayerIndex);
        if (chosenPlayerIndex == currentPlayer.getId()) {
            throw new InputMismatchException("chosenSelfError");
        }

        chosenPlayer.setHandVisibility(false);
        chosenPlayer.shuffleHand();
        game.setPlayer(chosenPlayer);

        int cardIndex = currentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);
    }
}