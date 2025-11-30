package domain.game;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.player.Hand;
import domain.player.Player;
import domain.player.PlayerController;
import domain.player.PlayerManager;

import java.util.List;
import java.util.Map;

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

    public int getNumberOfPlayers() {
        return gameEngine.getNumberOfPlayers();
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

    public int getNextPlayerId() {
        return gameEngine.getNextPlayerId();
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

    public void setPlayerTurns(int playerIndex, int turns) {
        gameEngine.setPlayerTurns(playerIndex, turns);
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

    public void emptyCurrentPlayerHand() {
        gameEngine.emptyCurrentPlayerHand();
    }

    public Boolean getCurrentPlayerHasNope() {
        return gameEngine.getCurrentPlayerHasNope();
    }
    
    public void setCurrentPlayerHasNope(Boolean hasNope) {
        gameEngine.setCurrentPlayerHasNope(hasNope);
    }

    public Map<Integer, List<Card>> getVisibleCards() {
        return gameEngine.getVisibleCards();
    }

    public void setGameOver(Boolean isGameOver) {
        gameEngine.setGameOver(isGameOver);
    }

    public Boolean isGameOver() {
        return gameEngine.isGameOver();
    }

    public PlayerManager getPlayerManager() {
        return gameEngine.getPlayerManager();
    }

    public DeckManager getDeckManager() {
        return gameEngine.getDeckManager();
    }

    public CardManager getCardManager() {
        return cardManager;
    }
}