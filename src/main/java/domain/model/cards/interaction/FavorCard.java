package domain.model.cards.interaction;

import domain.model.Game;
import domain.model.Player;
import domain.model.cards.Card;
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
        game.setPlayer(chosenPlayer);

        if (!Objects.equals(choosenCard.getName(), "Exploding Kitten")) {
            curentPlayer.addCard(choosenCard);
            game.setPlayer(curentPlayer);
            ui.displayMessage("addCard");
        } else {
            choosenCard.playCard(game, ui);
        }

        int cardIndex = curentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }
}