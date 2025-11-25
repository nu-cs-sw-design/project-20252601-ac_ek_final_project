package application;

import domain.model.ExpansionPack;
import domain.model.Game;
import domain.model.GameConfiguration;
import domain.model.Player;
import ui.UI;

import java.util.HashSet;
import java.util.Set;

public class GameController {
    private static final int ENGLISH = 0;
    private static final int FRENCH = 1;

    private final UI ui;
    private Game game;

    @SuppressWarnings("EI_EXPOSE_REP2") // UI is a shared service object, not mutable state
    public GameController(UI ui) {
        this.ui = ui;
    }

    public void startGame() {
        ui.displayMessage("welcomeMessage");
        int language = -1;
        while( language != FRENCH && language != ENGLISH ) {
            language = ui.promptPlayer("language");
        }

        if (language == FRENCH) {
            ui.changeLanguage("fr", "Fr");
        }

        GameConfiguration config = getGameConfiguration();
        
        if (this.game == null) {
            this.game = new Game(config.getPlayerCount(), ui, new domain.factory.DeckFactory(), config.getExpansionPacks());
        }

        while(!game.isGameOver()) {
            game.takeTurn();
        }

        ui.displayMessage("gameOver");
        Player winner = game.getWinner();
        if (winner != null) {
            ui.displayFormattedMessage("winner", winner.getId());
        }
    }

    private GameConfiguration getGameConfiguration() {
        GameConfiguration config = null;
        
        while (config == null) {
            try {
                Set<Integer> expansionNumbers = ui.promptExpansionPackNumbers();
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
                    ui.displayFormattedMessage("selectedExpansions", expansionNames.toString());
                }
                
                int playerCount = ui.promptPlayer("enterNumPlayers");
                
                config = new GameConfiguration(playerCount, expansionPacks);
                
            } catch (IllegalArgumentException e) {
                ui.displayMessage(e.getMessage());
            }
        }
        
        return config;
    }
}