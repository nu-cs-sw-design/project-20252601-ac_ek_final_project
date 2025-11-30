package application;

import domain.game.GameEngine;
import domain.player.Player;
import ui.UserInterface;

public class GameCreator {
    private final UserInterface UserInterface;
    private final GameEngine gameEngine;

    public GameCreator(GameEngine gameEngine, UserInterface UserInterface) {
        this.gameEngine = gameEngine;
        this.UserInterface = UserInterface;
    }

    public void startGame() {
        while(!gameEngine.isGameOver()) {
            gameEngine.takeTurn();
        }

        UserInterface.displayMessage("gameOver");
        Player winner = gameEngine.getWinner();
        if (winner != null) {
            UserInterface.displayFormattedMessage("winner", winner.getId());
        }
    }
}