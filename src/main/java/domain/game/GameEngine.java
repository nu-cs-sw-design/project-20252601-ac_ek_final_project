package domain.game;

import domain.cards.Card;
import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.player.Player;
import domain.player.PlayerManager;
import ui.GameUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameEngine {
    private final List<GameUI> observers;
    private final GameUI userInterface;
    private final Set<String> expansionIds;

    private final PlayerManager PlayerManager;
    private final DeckManager deckManager;
    private final NopeOperation nopeOperation;
    
    private final Game game;
    private static final int MINIMUM_PLAYERS = 1;
    
    public GameEngine(GameUI userInterface, DeckManager deckManager, PlayerManager PlayerManager, NopeOperation nopeOperation, Game game, Set<String> expansionIds) {
        this.userInterface = userInterface;
        this.observers = new ArrayList<>();
        this.observers.add(userInterface);
        this.deckManager = deckManager;
        this.PlayerManager = PlayerManager;
        this.nopeOperation = nopeOperation;
        this.game = game;
        this.expansionIds = expansionIds;
    }

    public void takeTurn() {
        Turn turn = new Turn(this, nopeOperation);
        turn.executeTurn();
    }

    public void addObserver(GameUI observer) {
        observers.add(observer);
    }

    public void removeObserver(GameUI observer) {
        observers.remove(observer);
    }

    public void notifyMessage(String key) {
        for (GameUI observer : observers) {
            observer.displayMessage(key);
        }
    }

    public void notifyFormattedMessage(String key, Object... args) {
        for (GameUI observer : observers) {
            observer.displayFormattedMessage(key, args);
        }
    }

    public void notifyClearScreen() {
        for (GameUI observer : observers) {
            observer.clearScreen();
        }
    }

    public GameUI getUserInterface() {
        return userInterface;
    }

    public void deletePlayer(int id) {
        PlayerManager.eliminatePlayer(game, id);
    }

    public Player getCurrentPlayer() {
        return PlayerManager.getCurrentPlayer(game);
    }

    public Player getPlayer(int id) {
        return PlayerManager.getPlayer(game, id);
    }

    public void setCurrentPlayer(Player player) {
        PlayerManager.setCurrentPlayer(game, player);
    }

    public void setPlayerTurns(int playerIndex, int turns) {
        PlayerManager.setPlayerTurns(game, playerIndex, turns);
    }

    public void setNextPlayerTurns(int turns) {
        PlayerManager.setNextPlayerTurns(game, turns);
    }

    public int getNextPlayerId() {
        return PlayerManager.getNextPlayerId(game);
    }

    public void setPlayer(Player player) {
        PlayerManager.updatePlayer(game, player);
    }

    public void nextPlayer() {
        PlayerManager.nextPlayer(game);
    }

    public int getNumberOfPlayers() {
        return PlayerManager.getPlayerCount(game);
    }

    public Deck getDeck() {
        return game.getDeck();
    }

    public Card drawTopCard() {
        return deckManager.drawTopCard(game.getDeck());
    }

    public boolean isDeckEmpty() {
        return game.getDeck().isEmpty();
    }

    public List<Player> getPlayers() {
        return PlayerManager.getPlayers(game);
    }

    public void setPlayers(List<Player> newPlayers) {
        PlayerManager.setPlayers(game, newPlayers);
    }

    public void setDeck(Deck deck) {
        game.setDeck(deck);
    }

    public void setNextPlayerTargetPlayer(Player targetPlayer) {
        PlayerManager.setNextPlayerTargetPlayer(game, targetPlayer);
    }

    public void setCurrentPlayerTurns(int i) {
        PlayerManager.setCurrentPlayerTurns(game, i);
    }

    public void emptyCurrentPlayerHand() {
        PlayerManager.emptyCurrentPlayerHand(game);
    }

    public void setCurrentPlayerHasNope(Boolean hasNope) {
        PlayerManager.setCurrentPlayerHasNope(game, hasNope);
    }

    public Boolean getCurrentPlayerHasNope() {
        return PlayerManager.getCurrentPlayerHasNope(game);
    }

    public Boolean isGameOver() {
        return game.isGameOver();
    }

    public void setGameOver(Boolean isGameOver) {
        game.setGameOver(isGameOver);
    }

    public Player getWinner() {
        if (PlayerManager.getPlayerCount(game) == MINIMUM_PLAYERS) {
            return PlayerManager.getCurrentPlayer(game);
        }
        return null;
    }

    public void removeCurrentPlayerCard(int index) {
        PlayerManager.removeCurrentPlayerCard(game, index);
    }

    public void addToCurrentPlayer(Card cardToAdd) {
        PlayerManager.addToCurrentPlayer(game, cardToAdd);
    }

    public Map<Integer, List<Card>> getVisibleCards() {
        return PlayerManager.getVisibleCards(game);
    }

    public int getDeckSize() {
        return deckManager.getDeckSize(game.getDeck());
    }
    
    public Game getGame() {
        return game;
    }

    public PlayerManager getPlayerManager() {
        return PlayerManager;
    }

    public DeckManager getDeckManager() {
        return deckManager;
    }
}