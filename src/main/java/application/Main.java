package application;

import domain.deck.DeckCreator;
import domain.deck.DeckManager;
import domain.game.GameConfiguration;
import domain.game.GameEngine;
import domain.game.Game;
import domain.game.NopeOperation;
import domain.player.PlayerManager;
import ui.GameUI;
import ui.UIRegistry;

import java.util.HashSet;
import java.util.Set;

public class Main {
    private static final int ENGLISH = 0;
    private static final int FRENCH = 1;

    public static void main(String[] args) {
        GameUI ui = selectUI(args);
        ui.initialize();

        ui.displayMessage("welcomeMessage");
        int language = -1;
        while (language != FRENCH && language != ENGLISH) {
            language = ui.promptPlayer("language");
        }

        if (language == FRENCH) {
            ui.changeLanguage("fr", "Fr");
        }

        GameConfiguration config = getGameConfiguration(ui);

        DeckCreator deckCreator = new DeckCreator();
        DeckManager deckManager = new DeckManager(deckCreator);
        PlayerManager playerManager = new PlayerManager();
        NopeOperation nopeOperation = new NopeOperation();
        Game game = new Game();

        GameEngine gameEngine = new GameEngine(ui, deckManager, playerManager, nopeOperation, game, config.getExpansionIds());

        var deck = deckManager.initializeDeck(config.getPlayerCount(), config.getExpansionIds());
        game.setDeck(deck);
        var players = playerManager.initializePlayers(config, deck, ui);
        game.setPlayers(players);
        deckManager.addRemainingCards(deck, config.getPlayerCount(), config.getExpansionIds());

        GameCreator gameCreator = new GameCreator(gameEngine, ui);
        gameCreator.startGame();
        
        ui.shutdown();
    }

    private static GameUI selectUI(String[] args) {
        if (args.length > 0) {
            String requestedUI = args[0].toLowerCase();
            if (UIRegistry.isRegistered(requestedUI)) {
                return UIRegistry.create(requestedUI).orElse(UIRegistry.createDefault());
            }
            System.out.println("Unknown UI type: " + requestedUI);
            System.out.println("Available types: " + UIRegistry.getAvailableTypes());
        }
        return UIRegistry.createDefault();
    }

    private static GameConfiguration getGameConfiguration(GameUI ui) {
        GameConfiguration config = null;
        
        while (config == null) {
            try {
                Set<Integer> expansionNumbers = ui.promptExpansionPackNumbers();
                Set<String> expansionIds = new HashSet<>();
                
                for (int number : expansionNumbers) {
                    if (!GameConfiguration.isValidExpansionNumber(number)) {
                        throw new IllegalArgumentException("invalidExpansionSelection");
                    }
                    expansionIds.add(GameConfiguration.getExpansionId(number));
                }
                
                if (!expansionIds.isEmpty()) {
                    StringBuilder expansionNames = new StringBuilder();
                    for (int number : expansionNumbers) {
                        if (expansionNames.length() > 0) {
                            expansionNames.append(", ");
                        }
                        expansionNames.append(GameConfiguration.getExpansionDisplayName(number));
                    }
                    ui.displayFormattedMessage("selectedExpansions", expansionNames.toString());
                }
                
                int humanPlayerCount = ui.promptPlayer("enterNumHumanPlayers");
                int aiPlayerCount = ui.promptPlayer("enterNumAIPlayers");
                
                config = new GameConfiguration(humanPlayerCount, aiPlayerCount, expansionIds);
                
            } catch (IllegalArgumentException e) {
                ui.displayMessage(e.getMessage());
            }
        }
        
        return config;
    }
}