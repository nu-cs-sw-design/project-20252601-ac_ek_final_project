package domain.model;

import domain.factory.DeckFactory;
import domain.model.cards.*;
import domain.service.DeckService;
import domain.service.GameStateService;
import domain.service.PlayerService;
import domain.service.TurnService;
import ui.UI;

import java.util.*;

public class Game {
    private final UI ui;
    private final Set<ExpansionPack> expansionPacks;

    private final PlayerService playerService;
    private final DeckService deckService;
    private final GameStateService gameStateService;

    @SuppressWarnings("EI_EXPOSE_REP2") // UI is a shared service object, not mutable state
    public Game(int numberOfPlayers, UI ui, DeckFactory deckFactory, Set<ExpansionPack> expansionPacks) {
        this.ui = ui;
        this.expansionPacks = new HashSet<>(expansionPacks);

        this.deckService = new DeckService(deckFactory);
        this.playerService = new PlayerService();
        this.gameStateService = new GameStateService();

        deckService.initializeDeck(numberOfPlayers, this.expansionPacks);
        
        playerService.initializePlayers(numberOfPlayers, deckService.getDeckForInitialization(), !expansionPacks.isEmpty());

        deckService.addRemainingCards(numberOfPlayers, this.expansionPacks);
    }

    public void takeTurn() {
        TurnService turnService = new TurnService(this, ui);
        turnService.executeTurn();
    }

    public void deletePlayer(int id) {
        playerService.eliminatePlayer(id);
    }

    public Player getCurrentPlayer() {
        return playerService.getCurrentPlayer();
    }

    public Player getPlayer(int id) {
        return playerService.getPlayer(id);
    }

    public void setCurrentPlayer(Player player) {
        playerService.setCurrentPlayer(player);
    }

    public void setPlayerTurns(int playerIndex, int turns) {
        playerService.setPlayerTurns(playerIndex, turns);
    }

    public void setNextPlayerTurns(int turns) {
        playerService.setNextPlayerTurns(turns);
    }

    public int getNextPlayerId() {
        return playerService.getNextPlayerId();
    }

    public void setPlayer(Player player) {
        playerService.updatePlayer(player);
    }

    public void nextPlayer() {
        playerService.nextPlayer();
    }

    public int getNumberOfPlayers() {
        return playerService.getPlayerCount();
    }

    public Deck getDeck() {
        return deckService.getDeck();
    }

    public Card drawTopCard() {
        return deckService.drawTopCard();
    }

    public boolean isDeckEmpty() {
        return deckService.isEmpty();
    }

    @SuppressWarnings("EI_EXPOSE_REP") // UI is a shared service object, not mutable state
    public UI getUI() {
        return this.ui;
    }

    public List<Player> getPlayers() {
        return playerService.getPlayers();
    }

    public void setPlayers(List<Player> newPlayers) {
        playerService.setPlayers(newPlayers);
    }

    public void setDeck(Deck deck) {
        deckService.setDeck(deck);
    }

    public void setNextPlayerTargetPlayer(Player targetPlayer) {
        playerService.setNextPlayerTargetPlayer(targetPlayer);
    }

    public void setCurrentPlayerTurns(int i) {
        playerService.setCurrentPlayerTurns(i);
    }

    public void emptyCurrentPlayerHand() {
        playerService.emptyCurrentPlayerHand();
    }

    public void setCurrentPlayerHasNope(Boolean hasNope) {
        playerService.setCurrentPlayerHasNope(hasNope);
    }

    public Boolean getCurrentPlayerHasNope() {
        return playerService.getCurrentPlayerHasNope();
    }

    public Boolean isGameOver() {
        return gameStateService.isGameOver();
    }

    public void setGameOver(Boolean isGameOver) {
        gameStateService.setGameOver(isGameOver);
    }

    public Player getWinner() {
        return gameStateService.getWinner(playerService);
    }

    public void removeCurrentPlayerCard(int index) {
        playerService.removeCurrentPlayerCard(index);
    }

    public void addToCurrentPlayer(Card cardToAdd) {
        playerService.addToCurrentPlayer(cardToAdd);
    }

    public Map<Integer, List<Card>> getVisibleCards() {
        return playerService.getVisibleCards();
    }

    public int getDeckSize() {
        return deckService.getDeckSize();
    }
}