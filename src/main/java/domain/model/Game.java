package domain.model;

import domain.factory.DeckFactory;
import domain.model.cards.*;
import domain.model.cards.special.DefuseCard;
import domain.service.TurnService;
import ui.UI;

import java.util.*;

public class Game {
    private final int numberOfPlayers;
    private final List<Player> players;
    private final UI ui;
    private final DeckFactory deckFactory;
    private final Set<ExpansionPack> expansionPacks;

    private Deck deck;
    private Player currentPlayer;
    private Boolean isGameOver = false;

    private static final int INITIAL_CARDS_BASE_GAME = 4;
    private static final int INITIAL_CARDS_WITH_EXPANSIONS = 8;
    int MINIMUM_PLAYERS = 1;
    int CURRENT_PLAYER_INDEX = 0;

    @SuppressWarnings("EI_EXPOSE_REP2") // UI is a shared service object, not mutable state
    public Game(int numberOfPlayers, UI ui, DeckFactory deckFactory, Set<ExpansionPack> expansionPacks) {
        this.players = new ArrayList<>(numberOfPlayers);
        this.numberOfPlayers = numberOfPlayers;
        this.ui = ui;
        this.deckFactory = deckFactory;
        this.expansionPacks = new HashSet<>(expansionPacks);

        int initialCardsPerPlayer = expansionPacks.isEmpty() ? INITIAL_CARDS_BASE_GAME : INITIAL_CARDS_WITH_EXPANSIONS;

        Deck localDeck = deckFactory.createDeck(numberOfPlayers, this.expansionPacks);
        localDeck.shuffle();
        this.deck = localDeck;

        boolean hasPartyPack = expansionPacks.contains(ExpansionPack.PARTY_PACK);
        int totalDefuseCards = deckFactory.getDefuseCardCount(numberOfPlayers, hasPartyPack);

        for (int i = 0; i < numberOfPlayers; i++) {
            Player newPlayer = new Player(i + 1, new ArrayList<Card>(initialCardsPerPlayer));

            newPlayer.addCard(new DefuseCard());

            for (int j = 0; j < initialCardsPerPlayer - 1; j++) {
                newPlayer.addCard(deck.drawTopCard());
            }

            players.add(newPlayer);

            if (i == CURRENT_PLAYER_INDEX) {
                currentPlayer = newPlayer;
            }
        }

        int remainingDefuseCards = totalDefuseCards - numberOfPlayers;
        for (int i = 0; i < remainingDefuseCards; i++) {
            localDeck.addCard(new DefuseCard());
        }

        boolean hasStreakingKittens = expansionPacks.contains(ExpansionPack.STREAKING_KITTENS);
        int explodingKittenCount = deckFactory.getExplodingKittenCount(numberOfPlayers, hasStreakingKittens);
        deckFactory.addExplodingKittens(localDeck, explodingKittenCount);

        if (expansionPacks.contains(ExpansionPack.IMPLODING_KITTENS)) {
            deckFactory.addImplodingKitten(localDeck);
        }

        localDeck.shuffle();
        this.deck = localDeck;
    }

    public void takeTurn() {
        TurnService turnService = new TurnService(this, ui);
        turnService.executeTurn();
    }

    public void deletePlayer(int id) {
        if (players.isEmpty()) {
            throw new NoSuchElementException("emptyPlayers");
        } else if (getNumberOfPlayers() == MINIMUM_PLAYERS) {
            throw new IllegalStateException("onePlayer");
        }

        for (Player player : players) {
            if (player.getId() == id) {
                players.remove(player);
                return;
            }
        }

        throw new NoSuchElementException("invalidPlayerID");
    }

    public Player getCurrentPlayer() {
        return new Player(currentPlayer);
    }

    public Player getPlayer(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return new Player(player);
            }
        }
        throw new NoSuchElementException("invalidPlayerID");
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = new Player(player);
    }

    public void setPlayerTurns(int playerIndex, int turns) {
        try {
            int playerId = getPlayer(playerIndex).getId();
            for (Player player : players) {
                if (player.getId() == playerId) {
                    player.setNumberOfTurns(turns);
                }
            }
        } catch (Exception e) {
            throw new IndexOutOfBoundsException("Invalid player index");
        }
    }

    public void setNextPlayerTurns(int turns) {
        setPlayerTurns(getNextPlayerId(), turns);
    }

    public int getNextPlayerId() {
        return players.get(1).getId();
    }

    public void setPlayer(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == player.getId()) {
                players.set(i, player);
                if (i == CURRENT_PLAYER_INDEX) {
                    setCurrentPlayer(player);
                }
                return;
            }
        }
        throw new NoSuchElementException("invalidPlayerID");
    }

    public void nextPlayer() {
        Collections.rotate(players, -1);
        currentPlayer = players.get(CURRENT_PLAYER_INDEX);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public Deck getDeck() {
        return deck.copy();
    }

    @SuppressWarnings("EI_EXPOSE_REP") // UI is a shared service object, not mutable state
    public UI getUI() {
        return this.ui;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public void setPlayers(List<Player> newPlayers) {
        players.clear();
        players.addAll(newPlayers);
    }

    public void setDeck(Deck deck) {
        this.deck = new Deck(deck);
    }

    public void setNextPlayerTargetPlayer(Player targetPlayer) {
        int targetIndex = players.indexOf(targetPlayer);
        if (targetIndex == 0) {
            throw new UnsupportedOperationException("invalidTargetPlayer");
        }
        if (targetIndex == -1){
            throw new UnsupportedOperationException("targetPlayerOutOfRange");
        }

        List<Player> reorderedPlayers = new ArrayList<>();
        reorderedPlayers.addAll(players.subList(targetIndex, players.size()));
        reorderedPlayers.addAll(players.subList(CURRENT_PLAYER_INDEX, targetIndex));

        setPlayers(reorderedPlayers);
        currentPlayer = players.get(CURRENT_PLAYER_INDEX);
    }

    public void setCurrentPlayerTurns(int i) {
        currentPlayer.setNumberOfTurns(i);
    }

    public void emptyCurrentPlayerHand() {
        currentPlayer.setHand(new ArrayList<>());
    }

    public void setCurrentPlayerHasNope(Boolean hasNope) {
        currentPlayer.setHasNope(hasNope);
    }

    public Boolean getCurrentPlayerHasNope() {
        return currentPlayer.getHasNope();
    }

    public Boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(Boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public Player getWinner() {
        if (players != null && players.size() == MINIMUM_PLAYERS) {
            return new Player(players.get(0));
        }
        return null;
    }

    public void removeCurrentPlayerCard(int index) {
        currentPlayer.removeCard(index);
    }

    public void addToCurrentPlayer(Card cardToAdd) {
        currentPlayer.addCard(cardToAdd);
    }


    public Map<Integer, List<Card>> getVisibleCards() {
        Map<Integer, List<Card>> visibleCardsMap = new HashMap<>();

        for (Player player : players) {
            int playerId = player.getId();
            List<Card> visibleHand = player.getVisibleHand();
            if (!visibleHand.isEmpty()) {
                visibleCardsMap.put(playerId, visibleHand);
            }
        }

        return visibleCardsMap;
    }
}