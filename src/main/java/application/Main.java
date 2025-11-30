package application;

import domain.deck.Deck;
import domain.player.Player;

import domain.deck.DeckCreator;
import domain.cards.ExpansionPack;
import domain.game.GameConfiguration;
import domain.game.GameEngine;
import domain.game.Game;
import domain.deck.DeckManager;
import domain.game.NopeOperation;
import domain.player.PlayerManager;
import ui.UserInterface;

import java.util.HashSet;
import java.util.Set;

public class Main {
    private static final int ENGLISH = 0;
    private static final int FRENCH = 1;

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();

        ui.displayMessage("welcomeMessage");
        int language = -1;
        while( language != FRENCH && language != ENGLISH ) {
            language = ui.promptPlayer("language");
        }

        if (language == FRENCH) {
            ui.changeLanguage("fr", "Fr");
        }

        GameConfiguration config = getGameConfiguration(ui);

        DeckCreator deckCreator = new DeckCreator();
        DeckManager deckManager = new DeckManager(deckCreator);
        PlayerManager PlayerManager = new PlayerManager();
        NopeOperation nopeOperation = new NopeOperation();
        Game game = new Game();

        GameEngine gameEngine = new GameEngine(
            ui, 
            deckManager, 
            PlayerManager, 
            nopeOperation,
            game, 
            config.getExpansionPacks()
        );

        var deck = deckManager.initializeDeck(config.getPlayerCount(), config.getExpansionPacks());
        game.setDeck(deck);
        var players = PlayerManager.initializePlayers(config, deck, ui);
        game.setPlayers(players);
        deckManager.addRemainingCards(deck, config.getPlayerCount(), config.getExpansionPacks());

        GameCreator gameCreator = new GameCreator(gameEngine, ui);
        gameCreator.startGame();
    }

    private static GameConfiguration getGameConfiguration(UserInterface ui) {
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
                
                int humanPlayerCount = ui.promptPlayer("enterNumHumanPlayers");
                int aiPlayerCount = ui.promptPlayer("enterNumAIPlayers");
                
                config = new GameConfiguration(humanPlayerCount, aiPlayerCount, expansionPacks);
                
            } catch (IllegalArgumentException e) {
                ui.displayMessage(e.getMessage());
            }
        }
        
        return config;
    }
}