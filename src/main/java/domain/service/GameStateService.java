package domain.service;

import domain.model.Player;

public class GameStateService {
    private boolean isGameOver = false;
    private final int MINIMUM_PLAYERS = 1;

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public Player getWinner(PlayerService playerService) {
        if (playerService.getPlayerCount() == MINIMUM_PLAYERS) {
            return playerService.getCurrentPlayer();
        }
        return null;
    }
}
