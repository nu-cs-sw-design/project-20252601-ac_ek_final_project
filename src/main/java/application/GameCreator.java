package application;

import domain.game.GameEngine;
import domain.player.Player;
import ui.GameUI;

public class GameCreator {
    private final GameUI userInterface;
    private final GameEngine gameEngine;

    public GameCreator(GameEngine gameEngine, GameUI userInterface) {
        this.gameEngine = gameEngine;
        this.userInterface = userInterface;
    }

    public void startGame() {
        while(!gameEngine.isGameOver()) {
            gameEngine.takeTurn();
        }

        userInterface.displayMessage("gameOver");
        Player winner = gameEngine.getWinner();
        if (winner != null) {
            userInterface.displayFormattedMessage("winner", winner.getId());
        }
    }
}