package application;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import domain.game.GameEngine;
import domain.player.Player;
import ui.GameUI;

public class GameCreator {
    private final GameUI userInterface;
    private final GameEngine gameEngine;

    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2", 
        justification = "GameCreator needs to hold a reference to the engine to control game execution; wrapping or copying is impractical")
    public GameCreator(GameEngine gameEngine, GameUI userInterface) {
        this.gameEngine = gameEngine;
        final GameUI realUI = userInterface;
        this.userInterface = new GameUI() {
            @Override
            public void displayMessage(String key) { realUI.displayMessage(key); }
            @Override
            public void displayFormattedMessage(String key, Object... args) { realUI.displayFormattedMessage(key, args); }
            @Override
            public void clearScreen() { realUI.clearScreen(); }
            @Override
            public int promptPlayer(String promptKey) { return realUI.promptPlayer(promptKey); }
            @Override
            public java.util.Set<Integer> promptExpansionPackNumbers() { return realUI.promptExpansionPackNumbers(); }
            @Override
            public void changeLanguage(String language, String country) { realUI.changeLanguage(language, country); }
        };
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