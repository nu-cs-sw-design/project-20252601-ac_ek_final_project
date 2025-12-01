package domain.game;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.player.Player;
import domain.player.PlayerManager;

import java.util.List;

@edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
    value = "EI_EXPOSE_REP2", 
    justification = "GameContext intentionally stores engine reference for delegation")
public class GameContext {
    private final GameEngine gameEngine;
    private final CardManager cardManager;

    public GameContext(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.cardManager = new CardManager();
    }

    public void displayMessage(String message) {
        gameEngine.notifyMessage(message);
    }

    public void displayFormattedMessage(String key, Object... args) {
        gameEngine.notifyFormattedMessage(key, args);
    }

    public int promptPlayer(String prompt) {
        return gameEngine.getCurrentPlayer().getController().getDecision(gameEngine, prompt);
    }

    public Player getCurrentPlayer() {
        return gameEngine.getCurrentPlayer();
    }

    public void setCurrentPlayer(Player player) {
        gameEngine.setCurrentPlayer(player);
    }

    public Player getPlayer(int id) {
        return gameEngine.getPlayer(id);
    }

    public void setPlayer(Player player) {
        gameEngine.setPlayer(player);
    }

    public void deletePlayer(int id) {
        gameEngine.deletePlayer(id);
    }

    public List<Player> getPlayers() {
        return gameEngine.getPlayers();
    }

    public void setPlayers(List<Player> players) {
        gameEngine.setPlayers(players);
    }

    public void nextPlayer() {
        gameEngine.nextPlayer();
    }

    public void setNextPlayerTargetPlayer(Player targetPlayer) {
        gameEngine.setNextPlayerTargetPlayer(targetPlayer);
    }

    public void setCurrentPlayerTurns(int turns) {
        gameEngine.setCurrentPlayerTurns(turns);
    }

    public void setNextPlayerTurns(int turns) {
        gameEngine.setNextPlayerTurns(turns);
    }

    public void removeCurrentPlayerCard(int index) {
        gameEngine.removeCurrentPlayerCard(index);
    }

    public void addToCurrentPlayer(Card cardToAdd) {
        gameEngine.addToCurrentPlayer(cardToAdd);
    }

    public Deck getDeck() {
        return gameEngine.getDeck();
    }

    public void setDeck(Deck deck) {
        gameEngine.setDeck(deck);
    }

    public PlayerManager getPlayerManager() {
        return gameEngine.getPlayerManager();
    }

    public DeckManager getDeckManager() {
        return gameEngine.getDeckManager();
    }

    public CardManager getCardManager() {
        return new CardManager(cardManager);
    }
}