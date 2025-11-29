package domain.model;

import domain.model.cards.Card;

import java.util.List;
import java.util.Map;

public class GameContext {
    private final Game game;

    public GameContext(Game game) {
        this.game = game;
    }

    public void displayMessage(String message) {
        game.notifyMessage(message);
    }

    public void displayFormattedMessage(String key, Object... args) {
        game.notifyFormattedMessage(key, args);
    }

    public int promptPlayer(String prompt) {
        return game.getInputProvider().promptPlayer(prompt);
    }

    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public Player getPlayer(int id) {
        return game.getPlayer(id);
    }

    public void setPlayer(Player player) {
        game.setPlayer(player);
    }

    public void deletePlayer(int id) {
        game.deletePlayer(id);
    }

    public int getNumberOfPlayers() {
        return game.getNumberOfPlayers();
    }

    public List<Player> getPlayers() {
        return game.getPlayers();
    }

    public void setPlayers(List<Player> players) {
        game.setPlayers(players);
    }

    public void setCurrentPlayer(Player player) {
        game.setCurrentPlayer(player);
    }

    public void nextPlayer() {
        game.nextPlayer();
    }

    public int getNextPlayerId() {
        return game.getNextPlayerId();
    }

    public void setNextPlayerTargetPlayer(Player targetPlayer) {
        game.setNextPlayerTargetPlayer(targetPlayer);
    }

    public void setCurrentPlayerTurns(int turns) {
        game.setCurrentPlayerTurns(turns);
    }

    public void setNextPlayerTurns(int turns) {
        game.setNextPlayerTurns(turns);
    }

    public void setPlayerTurns(int playerIndex, int turns) {
        game.setPlayerTurns(playerIndex, turns);
    }

    public void removeCurrentPlayerCard(int index) {
        game.removeCurrentPlayerCard(index);
    }

    public void addToCurrentPlayer(Card cardToAdd) {
        game.addToCurrentPlayer(cardToAdd);
    }

    public Deck getDeck() {
        return game.getDeck();
    }

    public void setDeck(Deck deck) {
        game.setDeck(deck);
    }

    public void emptyCurrentPlayerHand() {
        game.emptyCurrentPlayerHand();
    }

    public void setCurrentPlayerHasNope(Boolean hasNope) {
        game.setCurrentPlayerHasNope(hasNope);
    }

    public Boolean getCurrentPlayerHasNope() {
        return game.getCurrentPlayerHasNope();
    }

    public Map<Integer, List<Card>> getVisibleCards() {
        return game.getVisibleCards();
    }

    public void setGameOver(Boolean isGameOver) {
        game.setGameOver(isGameOver);
    }

    public Boolean isGameOver() {
        return game.isGameOver();
    }
}
