package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

import java.util.InputMismatchException;
import java.util.Objects;

public class FavorCard extends Card{
    private static final int[] COUNTS = {2, 4, 6};

    @Override
    public String getName() {
        return "Favor";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("favorCard");

        Player curentPlayer = game.getCurrentPlayer();
        int chosenPlayerID = ui.promptPlayer("targetPlayerId");
        if (chosenPlayerID == curentPlayer.getId()) {
            throw new InputMismatchException("chosenSelfError");
        }

        Player chosenPlayer = game.getPlayer(chosenPlayerID);
        int chosenCardIndex = ui.promptPlayer("chosenPlayerCardIndex");
        Card choosenCard = chosenPlayer.chooseCard(chosenCardIndex);
        chosenPlayer.removeCard(chosenCardIndex);
        curentPlayer.addCard(choosenCard);
        game.setPlayer(chosenPlayer);
        game.setPlayer(curentPlayer);
        int cardIndex = curentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);

        if (!Objects.equals(choosenCard.getName(), "Exploding Kitten")) {
            ui.displayMessage("addCard");
        } else {
            choosenCard.playCard(game, ui);
        }
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }
}