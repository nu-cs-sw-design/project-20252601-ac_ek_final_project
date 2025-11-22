package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

public class MarkCard extends Card {
    @Override
    public String getName() {
        return "Mark";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("markCard");

        int targetPlayerId = ui.promptPlayer("targetPlayerId");
        if (targetPlayerId <= 0 || targetPlayerId > game.getPlayers().size()) {
            throw new UnsupportedOperationException("invalidPlayerID");
        }

        Player targetPlayer = game.getPlayer(targetPlayerId);
        if (targetPlayerId == game.getCurrentPlayer().getId()) {
            throw new UnsupportedOperationException("chosenSelfError");
        }

        int targetCardIndex = ui.promptPlayer("targetCardIndex");
        if (targetCardIndex < 0 || targetCardIndex >= targetPlayer.getHandCount()) {
            throw new UnsupportedOperationException("invalidCardIndex");
        }

        Card targetCard = targetPlayer.chooseCard(targetCardIndex);
        if (targetPlayer.getVisibleHand().contains(targetCard)) {
            throw new UnsupportedOperationException("alreadyVisible");
        }

        targetPlayer.addVisibleCard(targetCard);
        game.setPlayer(targetPlayer);

        Player currentPlayer = game.getCurrentPlayer();
        int cardIndex = currentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);
    }
}