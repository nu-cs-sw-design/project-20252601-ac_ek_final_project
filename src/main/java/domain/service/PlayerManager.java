package domain.service;

import domain.model.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class PlayerManager {
    private final List<Player> players;
    private Player currentPlayer;
    private static final int CURRENT_PLAYER_INDEX = 0;
    private static final int MINIMUM_PLAYERS = 1;

    public PlayerManager(List<Player> players) {
        this.players = new ArrayList<>(players);
        if (!players.isEmpty()) {
            this.currentPlayer = new Player(players.get(CURRENT_PLAYER_INDEX));
        }
    }

    public Player getCurrentPlayer() {
        return new Player(currentPlayer);
    }

    public Player getNextPlayer() {
        if (players.size() > 1) {
            return new Player(players.get(1));
        }
        throw new NoSuchElementException("No next player available");
    }

    public void eliminatePlayer(int id) {
        if (players.isEmpty()) {
            throw new NoSuchElementException("emptyPlayers");
        }
        if (getPlayerCount() == MINIMUM_PLAYERS) {
            throw new IllegalStateException("onePlayer");
        }

        for (Player player : players) {
            if (player.getId() == id) {
                players.remove(player);
                if (!players.isEmpty()) {
                    currentPlayer = new Player(players.get(CURRENT_PLAYER_INDEX));
                }
                return;
            }
        }

        throw new NoSuchElementException("invalidPlayerID");
    }

    public List<Player> getAllActivePlayers() {
        return List.copyOf(players);
    }

    public Player getPlayerById(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return new Player(player);
            }
        }
        throw new NoSuchElementException("invalidPlayerID");
    }

    public void updatePlayer(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == player.getId()) {
                players.set(i, new Player(player));
                if (i == CURRENT_PLAYER_INDEX) {
                    currentPlayer = new Player(player);
                }
                return;
            }
        }
        throw new NoSuchElementException("invalidPlayerID");
    }

    public void advanceToNextPlayer() {
        Collections.rotate(players, -1);
        currentPlayer = new Player(players.get(CURRENT_PLAYER_INDEX));
    }
    public int getPlayerCount() {
        return players.size();
    }

    public void setNextPlayer(Player targetPlayer) {
        int targetIndex = -1;
        
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == targetPlayer.getId()) {
                targetIndex = i;
                break;
            }
        }

        if (targetIndex == CURRENT_PLAYER_INDEX) {
            throw new UnsupportedOperationException("invalidTargetPlayer");
        }
        if (targetIndex == -1) {
            throw new UnsupportedOperationException("targetPlayerOutOfRange");
        }

        List<Player> reorderedPlayers = new ArrayList<>();
        reorderedPlayers.addAll(players.subList(targetIndex, players.size()));
        reorderedPlayers.addAll(players.subList(CURRENT_PLAYER_INDEX, targetIndex));

        players.clear();
        players.addAll(reorderedPlayers);
        currentPlayer = new Player(players.get(CURRENT_PLAYER_INDEX));
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = new Player(player);
    }

    public int getNextPlayerId() {
        if (players.size() > 1) {
            return players.get(1).getId();
        }
        throw new NoSuchElementException("No next player available");
    }

    public void setPlayerTurns(int playerId, int turns) {
        Player player = getPlayerById(playerId);
        player.setNumberOfTurns(turns);
        updatePlayer(player);
    }

    public void setNextPlayerTurns(int turns) {
        setPlayerTurns(getNextPlayerId(), turns);
    }

    public void setAllPlayers(List<Player> newPlayers) {
        players.clear();
        players.addAll(newPlayers);
        if (!players.isEmpty()) {
            currentPlayer = new Player(players.get(CURRENT_PLAYER_INDEX));
        }
    }
}
