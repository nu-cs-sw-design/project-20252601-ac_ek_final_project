package application;

import domain.model.ExpansionPack;
import domain.model.Game;
import domain.model.GameConfiguration;
import domain.model.Player;
import ui.GameObserver;
import ui.InputProvider;

import java.util.HashSet;
import java.util.Set;

public class GameController {
    private static final int ENGLISH = 0;
    private static final int FRENCH = 1;

    private final InputProvider inputProvider;
    private final GameObserver gameObserver;
    private Game game;

    @SuppressWarnings("EI_EXPOSE_REP2") // UI is a shared service object, not mutable state
    public GameController(InputProvider inputProvider, GameObserver gameObserver) {
        this.inputProvider = inputProvider;
        this.gameObserver = gameObserver;
    }

    public void startGame() {
        gameObserver.displayMessage("welcomeMessage");
        int language = -1;
        while( language != FRENCH && language != ENGLISH ) {
            language = inputProvider.promptPlayer("language");
        }

        if (language == FRENCH) {
            gameObserver.changeLanguage("fr", "Fr");
        }

        GameConfiguration config = getGameConfiguration();
        
        if (this.game == null) {
            this.game = new Game(config.getPlayerCount(), config.getAIPlayerCount(), inputProvider, gameObserver, new domain.factory.DeckFactory(), config.getExpansionPacks());
        }

        while(!game.isGameOver()) {
            game.takeTurn();
        }

        gameObserver.displayMessage("gameOver");
        Player winner = game.getWinner();
        if (winner != null) {
            gameObserver.displayFormattedMessage("winner", winner.getId());
        }
    }

    private GameConfiguration getGameConfiguration() {
        GameConfiguration config = null;
        
        while (config == null) {
            try {
                Set<Integer> expansionNumbers = inputProvider.promptExpansionPackNumbers();
                Set<ExpansionPack> expansionPacks = new HashSet<>();
                
                for (int number : expansionNumbers) {
                    if (!GameConfiguration.isValidExpansionNumber(number)) {
                        throw new IllegalArgumentException("invalidExpansionSelection");
                    }
                    expansionPacks.add(GameConfiguration.getExpansionPack(number));
                }
                
                if (!expansionPacks.isEmpty()) {
                    StringBuilder expansionNames = new StringBuilder();
                    for (ExpansionPack pack : expansionPacks) {
                        if (expansionNames.length() > 0) {
                            expansionNames.append(", ");
                        }
                        expansionNames.append(pack.getDisplayName());
                    }
                    gameObserver.displayFormattedMessage("selectedExpansions", expansionNames.toString());
                }
                
                int humanPlayerCount = inputProvider.promptPlayer("enterNumHumanPlayers");
                int aiPlayerCount = inputProvider.promptPlayer("enterNumAIPlayers");
                
                config = new GameConfiguration(humanPlayerCount, aiPlayerCount, expansionPacks);
                
            } catch (IllegalArgumentException e) {
                gameObserver.displayMessage(e.getMessage());
            }
        }
        
        return config;
    }
}