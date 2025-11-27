package domain.factory;

import domain.model.Deck;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.special.DefuseCard;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {

    public List<Player> createPlayers(int numberOfPlayers, Deck deck, int initialCardsPerPlayer) {
        List<Player> players = new ArrayList<>(numberOfPlayers);
        
        for (int i = 0; i < numberOfPlayers; i++) {
            Player newPlayer = createPlayer(i + 1, deck, initialCardsPerPlayer);
            players.add(newPlayer);
        }
        
        return players;
    }
    
    private Player createPlayer(int playerId, Deck deck, int initialCardsPerPlayer) {
        Player player = new Player(playerId, new ArrayList<Card>(initialCardsPerPlayer));
        
        player.addCard(new DefuseCard());
        
        for (int i = 0; i < initialCardsPerPlayer - 1; i++) {
            player.addCard(deck.drawTopCard());
        }
        
        return player;
    }
    
    public int getInitialCardsPerPlayer(boolean hasExpansions) {
        return hasExpansions ? 8 : 4;
    }
}
