package domain.model;

import domain.factory.DeckFactory;
import domain.model.cards.*;
import domain.model.cards.special.DefuseCard;
import domain.service.PlayerManager;
import domain.service.TurnService;
import ui.UI;

import java.util.*;

public class Game {
    private final int numberOfPlayers;
    private final PlayerManager playerManager;
    private final UI ui;
    private final DeckFactory deckFactory;
    private final Set<ExpansionPack> expansionPacks;

    private Deck deck;
    private Boolean isGameOver = false;

    private static final int INITIAL_CARDS_BASE_GAME = 4;
    private static final int INITIAL_CARDS_WITH_EXPANSIONS = 8;
    int MINIMUM_PLAYERS = 1;
    int CURRENT_PLAYER_INDEX = 0;

    @SuppressWarnings("EI_EXPOSE_REP2") // UI is a shared service object, not mutable state
    public Game(int numberOfPlayers, UI ui, DeckFactory deckFactory, Set<ExpansionPack> expansionPacks) {
        List<Player> players = new ArrayList<>(numberOfPlayers);
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
        }

        this.playerManager = new PlayerManager(players);

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
        playerManager.eliminatePlayer(id);
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

    public void setPlayer(Player player) {
        playerManager.updatePlayer(player);
    }

    public void nextPlayer() {
        playerManager.advanceToNextPlayer();
    }

    public int getNumberOfPlayers() {
        return playerManager.getPlayerCount();
    }

    public Deck getDeck() {
        return deck.copy();
    }

    @SuppressWarnings("EI_EXPOSE_REP") // UI is a shared service object, not mutable state
    public UI getUI() {
        return this.ui;
    }

    public List<Player> getPlayers() {
        return playerManager.getAllActivePlayers();
    }

    public void setPlayers(List<Player> newPlayers) {
        playerManager.setAllPlayers(newPlayers);
    }

    public void setDeck(Deck deck) {
        this.deck = new Deck(deck);
    }

    public void setNextPlayerTargetPlayer(Player targetPlayer) {
        playerManager.setNextPlayer(targetPlayer);
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

    public Boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(Boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public Player getWinner() {
        if (playerManager.getPlayerCount() == MINIMUM_PLAYERS) {
            return playerManager.getCurrentPlayer();
        }
        return null;
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