package domain.player;

import domain.cards.Card;
import domain.deck.Deck;
import domain.game.Game;
import domain.game.GameConfiguration;
import ui.GameUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class PlayerManager {
    private final PlayerCreator playerCreator;
    private static final int CURRENT_PLAYER_INDEX = 0;
    private static final int MINIMUM_PLAYERS = 1;

    public PlayerManager() {
        this.playerCreator = new PlayerCreator();
    }

    public List<Player> initializePlayers(GameConfiguration config, Deck deck, GameUI userInterface) {
        return playerCreator.createPlayers(config, deck, userInterface);
    }

    public Player getCurrentPlayer(Game state) {
        List<Player> players = state.getPlayers();
        if (players.isEmpty()) {
            throw new NoSuchElementException("No players available");
        }
        return new Player(players.get(CURRENT_PLAYER_INDEX));
    }

    public void setCurrentPlayer(Game state, Player player) {
        List<Player> players = new ArrayList<>(state.getPlayers());
        if (!players.isEmpty()) {
            players.set(CURRENT_PLAYER_INDEX, new Player(player));
            state.setPlayers(players);
        }
    }

    public Player getPlayer(Game state, int id) {
        for (Player player : state.getPlayers()) {
            if (player.getId() == id) {
                return new Player(player);
            }
        }
        throw new NoSuchElementException("invalidPlayerID");
    }

    public List<Player> getPlayers(Game state) {
        return state.getPlayers();
    }

    public void setPlayers(Game state, List<Player> newPlayers) {
        state.setPlayers(newPlayers);
    }

    public void setPlayerTurns(Game state, int playerId, int turns) {
        try {
            Player player = getPlayer(state, playerId);
            player.setNumberOfTurns(turns);
            updatePlayer(state, player);
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Invalid player index");
        }
    }

    public void setNextPlayerTurns(Game state, int turns) {
        int nextPlayerId = getNextPlayerId(state);
        setPlayerTurns(state, nextPlayerId, turns);
    }

    public int getNextPlayerId(Game state) {
        List<Player> players = state.getPlayers();
        if (players.size() > 1) {
            return players.get(1).getId();
        }
        throw new NoSuchElementException("No next player available");
    }

    public void updatePlayer(Game state, Player player) {
        List<Player> players = new ArrayList<>(state.getPlayers());
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == player.getId()) {
                players.set(i, new Player(player));
                state.setPlayers(players);
                return;
            }
        }
        throw new NoSuchElementException("invalidPlayerID");
    }

    public void nextPlayer(Game state) {
        List<Player> players = new ArrayList<>(state.getPlayers());
        Collections.rotate(players, -1);
        state.setPlayers(players);
    }

    public int getPlayerCount(Game state) {
        return state.getPlayers().size();
    }

    public void setNextPlayerTargetPlayer(Game state, Player targetPlayer) {
        List<Player> players = new ArrayList<>(state.getPlayers());
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

        state.setPlayers(reorderedPlayers);
    }

    public void eliminatePlayer(Game state, int id) {
        List<Player> players = new ArrayList<>(state.getPlayers());
        if (players.isEmpty()) {
            throw new NoSuchElementException("emptyPlayers");
        }
        if (players.size() == MINIMUM_PLAYERS) {
            throw new IllegalStateException("onePlayer");
        }

        for (Player player : players) {
            if (player.getId() == id) {
                players.remove(player);
                state.setPlayers(players);
                return;
            }
        }

        throw new NoSuchElementException("invalidPlayerID");
    }

    public void setCurrentPlayerTurns(Game state, int turns) {
        Player current = getCurrentPlayer(state);
        current.setNumberOfTurns(turns);
        updatePlayer(state, current);
    }

    public void emptyCurrentPlayerHand(Game state) {
        Player current = getCurrentPlayer(state);
        current.setHand(new ArrayList<>());
        updatePlayer(state, current);
    }

    public Boolean getCurrentPlayerHasNope(Game state) {
        return getCurrentPlayer(state).getHasNope();
    }

    public void setCurrentPlayerHasNope(Game state, Boolean hasNope) {
        Player current = getCurrentPlayer(state);
        current.setHasNope(hasNope);
        updatePlayer(state, current);
    }

    public void addToCurrentPlayer(Game state, Card cardToAdd) {
        Player current = getCurrentPlayer(state);
        addCard(current, cardToAdd);
        updatePlayer(state, current);
    }
    
    public void removeCurrentPlayerCard(Game state, int index) {
        Player current = getCurrentPlayer(state);
        removeCard(current, index);
        updatePlayer(state, current);
    }

    public Map<Integer, List<Card>> getVisibleCards(Game state) {
        Map<Integer, List<Card>> visibleCardsMap = new HashMap<>();

        for (Player player : state.getPlayers()) {
            int playerId = player.getId();
            List<Card> visibleHand = getVisibleHand(player);
            if (!visibleHand.isEmpty()) {
                visibleCardsMap.put(playerId, visibleHand);
            }
        }

        return visibleCardsMap;
    }

    public int addCard(Player player, Card card) {
        player.getHandObject().addCard(card);
        return player.getHandObject().size();
    }

    public int addVisibleCard(Player player, Card card) {
        player.getHandObject().addVisibleCard(card);
        return player.getHandObject().getVisibleCards().size();
    }

    public List<Card> getVisibleHand(Player player) {
        return new ArrayList<>(player.getHandObject().getVisibleCards());
    }

    public int removeCard(Player player, int index) {
        player.getHandObject().removeCard(index);
        return player.getHandObject().size();
    }

    public Card chooseCard(Player player, int index) {
        return player.getHandObject().getCard(index);
    }

    public int hasCard(Player player, String card) {
        return player.getHandObject().findCardIndex(card);
    }

    public boolean hasTwoOf(Player player, String cardName) {
        return player.getHandObject().hasTwoOf(cardName);
    }

    public void shuffleHand(Player player) {
        player.getHandObject().shuffle();
    }

    public boolean isHandEmpty(Player player) {
        return player.getHandObject().isEmpty();
    }

    public int getHandCount(Player player) {
        return player.getHandObject().size();
    }

    public void decreaseTurnByOne(Player player) {
        if (player.getNumberOfTurns() < 1) {
            throw new UnsupportedOperationException("nonNegTurns");
        }
        player.setNumberOfTurns(player.getNumberOfTurns() - 1);
    }

    public boolean isTurnOver(Player player) {
        return player.getNumberOfTurns() == 0;
    }

    public List<Card> getHand(Player player) {
        if (!player.getHandVisibility()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(player.getHandObject().getCards());
    }
}