package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseCard extends Card{
    @Override
    public String getName() {
        return "Reverse";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("reverseCard");

        List<Player> players = new ArrayList<>(game.getPlayers());
        if (players.isEmpty()) {
            throw new IllegalStateException("emptyPlayers");
        }

        Player currentPlayer = game.getCurrentPlayer();
        int cardIndex = currentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);

        if (players.size() == 2) {
            int turns = currentPlayer.getNumberOfTurns();
            if (turns > 0) {
                game.setCurrentPlayerTurn(turns - 1);
            }
        }
        else {
            Collections.reverse(players);
            game.setPlayers(players);
        }
    }
}