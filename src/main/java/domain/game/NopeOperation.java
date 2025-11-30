package domain.game;

import domain.cards.Card;
import domain.player.Player;
import domain.player.PlayerManager;

import java.util.List;

public class NopeOperation {
    private static final String NOPE_CARD_NAME = "Nope";
    private static final int NOT_FOUND = -1;

    private final PlayerManager playerManager;

    public NopeOperation() {
        this.playerManager = new PlayerManager();
    }

    public boolean checkForNopeInterruption(GameEngine game, Card cardToPlay) {
        Player currentPlayer = game.getCurrentPlayer();
        return promptPlayersForNope(game, currentPlayer.getId());
    }

    private boolean promptPlayersForNope(GameEngine game, int excludedPlayerId) {
        List<Player> players = game.getPlayers();

        for (Player player : players) {
            if (player.getId() == excludedPlayerId) {
                continue;
            }

            int nopeIndex = playerManager.hasCard(player, NOPE_CARD_NAME);
            if (nopeIndex == NOT_FOUND) {
                continue;
            }

            game.notifyFormattedMessage("player", player.getId());
            Action action = player.getController().getNopeAction(game);

            if (action.getType() == Action.Type.NOPE) {
                return executeNope(game, player, nopeIndex);
            }
        }

        return false;
    }

    private boolean executeNope(GameEngine game, Player nopingPlayer, int nopeIndex) {
        Card nopeCard = playerManager.chooseCard(nopingPlayer, nopeIndex);
        playerManager.removeCard(nopingPlayer, nopeIndex);
        game.setPlayer(nopingPlayer);

        nopeCard.playCard(game);
        game.notifyMessage("playedNope");

        boolean nopeWasCountered = promptPlayersForNope(game, nopingPlayer.getId());

        return !nopeWasCountered;
    }
}