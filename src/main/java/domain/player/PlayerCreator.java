package domain.player;

import domain.cards.Card;
import domain.cards.implementations.DefuseCard;
import domain.deck.Deck;
import domain.game.GameConfiguration;
import ui.GameUI;

import java.util.ArrayList;
import java.util.List;

public class PlayerCreator {
    private final int INITIAL_CARDS_WITH_EXPANSIONS = 8;
    private final int INITIAL_CARDS_WITHOUT_EXPANSIONS = 4;

    public List<Player> createPlayers(GameConfiguration config, Deck deck, GameUI userInterface) {
        int numberOfPlayers = config.getPlayerCount();
        int numberOfAIPlayers = config.getAIPlayerCount();
        int initialCardsPerPlayer = getInitialCardsPerPlayer(!config.getExpansionIds().isEmpty());
        
        List<Player> players = new ArrayList<>(numberOfPlayers);
        
        int firstAIPlayerIndex = numberOfPlayers - numberOfAIPlayers;

        for (int i = 0; i < numberOfPlayers; i++) {
            Player newPlayer = createPlayer(i + 1, deck, initialCardsPerPlayer);
            if (i >= firstAIPlayerIndex) {
                newPlayer.setAI(true);
                newPlayer.setController(new PlayerControllerAI());
            } else {
                newPlayer.setController(new PlayerControllerHuman(userInterface));
            }
            players.add(newPlayer);
        }
        
        return players;
    }
    
    private Player createPlayer(int playerId, Deck deck, int initialCardsPerPlayer) {
        Player player = new Player(playerId, new ArrayList<Card>(initialCardsPerPlayer));
        
        player.getHandObject().addCard(new DefuseCard());
        for (int i = 0; i < initialCardsPerPlayer - 1; i++) {
            player.getHandObject().addCard(deck.drawTopCard());
        }
        
        return player;
    }
    
    public int getInitialCardsPerPlayer(boolean hasExpansions) {
        return hasExpansions ? INITIAL_CARDS_WITH_EXPANSIONS : INITIAL_CARDS_WITHOUT_EXPANSIONS;
    }
}