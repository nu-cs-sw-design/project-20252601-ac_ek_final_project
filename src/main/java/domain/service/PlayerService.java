package domain.service;

import domain.factory.PlayerFactory;
import domain.model.cards.Card;
import domain.model.Deck;
import domain.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerService {
    private PlayerManager playerManager;
    private final PlayerFactory playerFactory;

    public PlayerService() {
        this.playerFactory = new PlayerFactory();
    }

    public void initializePlayers(int numberOfPlayers, Deck deck, boolean hasExpansions) {
        int initialCardsPerPlayer = playerFactory.getInitialCardsPerPlayer(hasExpansions);
        List<Player> players = playerFactory.createPlayers(numberOfPlayers, deck, initialCardsPerPlayer);
        this.playerManager = new PlayerManager(players);
    }

    public Player getCurrentPlayer() {
        return playerManager.getCurrentPlayer();
    }

    public Player getPlayer(int id) {
        return playerManager.getPlayerById(id);
    }

    public void setCurrentPlayer(Player player) {
        playerManager.setCurrentPlayer(player);
    }

    public void setPlayerTurns(int playerIndex, int turns) {
        try {
            int playerId = getPlayer(playerIndex).getId();
            playerManager.setPlayerTurns(playerId, turns);
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Invalid player index");
        }
    }

    public void setNextPlayerTurns(int turns) {
        playerManager.setNextPlayerTurns(turns);
    }

    public int getNextPlayerId() {
        return playerManager.getNextPlayerId();
    }

    public void updatePlayer(Player player) {
        playerManager.updatePlayer(player);
    }

    public void nextPlayer() {
        playerManager.advanceToNextPlayer();
    }

    public int getPlayerCount() {
        return playerManager.getPlayerCount();
    }

    public List<Player> getPlayers() {
        return playerManager.getAllActivePlayers();
    }

    public void setPlayers(List<Player> newPlayers) {
        playerManager.setAllPlayers(newPlayers);
    }

    public void setNextPlayerTargetPlayer(Player targetPlayer) {
        playerManager.setNextPlayer(targetPlayer);
    }

    public void eliminatePlayer(int id) {
        playerManager.eliminatePlayer(id);
    }

    public void setCurrentPlayerTurns(int i) {
        Player current = playerManager.getCurrentPlayer();
        current.setNumberOfTurns(i);
        playerManager.updatePlayer(current);
    }

    public void emptyCurrentPlayerHand() {
        Player current = playerManager.getCurrentPlayer();
        current.setHand(new ArrayList<>());
        playerManager.updatePlayer(current);
    }

    public void setCurrentPlayerHasNope(Boolean hasNope) {
        Player current = playerManager.getCurrentPlayer();
        current.setHasNope(hasNope);
        playerManager.updatePlayer(current);
    }

    public Boolean getCurrentPlayerHasNope() {
        return playerManager.getCurrentPlayer().getHasNope();
    }

    public void removeCurrentPlayerCard(int index) {
        Player current = playerManager.getCurrentPlayer();
        current.removeCard(index);
        playerManager.updatePlayer(current);
    }

    public void addToCurrentPlayer(Card cardToAdd) {
        Player current = playerManager.getCurrentPlayer();
        current.addCard(cardToAdd);
        playerManager.updatePlayer(current);
    }

    public Map<Integer, List<Card>> getVisibleCards() {
        Map<Integer, List<Card>> visibleCardsMap = new HashMap<>();

        for (Player player : playerManager.getAllActivePlayers()) {
            int playerId = player.getId();
            List<Card> visibleHand = player.getVisibleHand();
            if (!visibleHand.isEmpty()) {
                visibleCardsMap.put(playerId, visibleHand);
            }
        }

        return visibleCardsMap;
    }
}
