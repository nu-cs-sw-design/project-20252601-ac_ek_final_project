package domain.service;

import domain.model.cards.Card;
import domain.model.Game;
import domain.model.Player;
import domain.model.cards.special.ExplodingKittenCard;
import domain.model.cards.special.ImplodingKittenCard;
import ui.UI;

import java.util.List;

public class TurnService {
    private final Game game;
    private final UI ui;

    public TurnService(Game game, UI ui) {
        this.game = game;
        this.ui = ui;
    }

    public void executeTurn() {
        initializeTurn();
        handleCardPlayingPhase();
        handleCardDrawingPhase();
        finalizeTurn();
    }

    private void initializeTurn() {
        ui.clearScreen();
        displayGameInfo();
        ui.displayFormattedMessage("deckSize", game.getDeckSize());
        Player currentPlayer = game.getCurrentPlayer();
        ui.displayFormattedMessage("player", currentPlayer.getId());
        ui.displayMessage("turnStart");
        displayMarkCards();
    }

    private void handleCardPlayingPhase() {
        Player currentPlayer = game.getCurrentPlayer();
        if (currentPlayer.isHandEmpty()) {
            return;
        }

        int playCardYes = 1;
        int playCardChoice = playCardYes;

        while (playCardChoice == playCardYes && currentPlayer.getNumberOfTurns() > 0 && !currentPlayer.isHandEmpty()) {
            ui.displayMessage("currentHand");
            displayPlayerHand(currentPlayer, currentPlayer.getHandVisibility());
            playCardChoice = ui.promptPlayer("playCardPrompt");

            if (playCardChoice == playCardYes) {
                boolean playerEliminated = attemptToPlayCard();
                if (playerEliminated) {
                    return;
                }
                currentPlayer = game.getCurrentPlayer();
            }
        }
    }

    private boolean attemptToPlayCard() {
        boolean validCardPlayed = false;
        Player currentPlayer = game.getCurrentPlayer();

        while (!validCardPlayed && !currentPlayer.isHandEmpty()) {
            int cardIndex = ui.promptPlayer("chooseCardPrompt");
            
            try {
                Card selectedCard = currentPlayer.chooseCard(cardIndex);
                boolean actionCanceled = checkForNopeInterruption(cardIndex);

                if (!actionCanceled) {
                    boolean playerEliminated = executeCardAction(selectedCard);
                    validCardPlayed = true;
                    
                    if (playerEliminated) {
                        return true;
                    }
                } else {
                    validCardPlayed = true;
                }
            } catch (IndexOutOfBoundsException e) {
                ui.displayMessage("invalidCardIndex");
            }
            currentPlayer = game.getCurrentPlayer();
        }
        
        return false;
    }

    private boolean checkForNopeInterruption(int cardIndex) {
        Player currentPlayer = game.getCurrentPlayer();
        List<Player> players = game.getPlayers();
        
        for (Player player : players) {
            if (player.getId() != currentPlayer.getId()) {
                int nopeIndex = player.hasCard("Nope");
                if (nopeIndex != -1) {
                    ui.displayFormattedMessage("player", player.getId());
                    int wantsToPlay = ui.promptPlayer("chooseNope");
                    
                    if (wantsToPlay == 1) {
                        boolean actionCanceled = playNopeCard(player, nopeIndex);
                        
                        if (actionCanceled) {
                            currentPlayer = game.getCurrentPlayer();
                            currentPlayer.removeCard(cardIndex);
                            game.setPlayer(currentPlayer);
                        }
                        
                        return actionCanceled;
                    }
                }
            }
        }
        return false;
    }

    private boolean playNopeCard(Player nopingPlayer, int nopeIndex) {
        Card nopeCard = nopingPlayer.chooseCard(nopeIndex);
        nopingPlayer.removeCard(nopeIndex);
        game.setPlayer(nopingPlayer);
        nopeCard.playCard(game, ui);
        ui.displayMessage("playedNope");
        
        boolean nopeWasNoped = checkForCounterNope(nopingPlayer);
        
        return !nopeWasNoped;
    }

    private boolean checkForCounterNope(Player lastNopingPlayer) {
        List<Player> players = game.getPlayers();
        
        for (Player player : players) {
            if (player.getId() != lastNopingPlayer.getId()) {
                int nopeIndex = player.hasCard("Nope");
                if (nopeIndex != -1) {
                    ui.displayFormattedMessage("player", player.getId());
                    int wantsToPlay = ui.promptPlayer("chooseNope");
                    
                    if (wantsToPlay == 1) {
                        Card counterNope = player.chooseCard(nopeIndex);
                        player.removeCard(nopeIndex);
                        game.setPlayer(player);
                        counterNope.playCard(game, ui);
                        ui.displayMessage("playedNope");
                        
                        boolean counterNopeWasNoped = checkForCounterNope(player);
                        return !counterNopeWasNoped;
                    }
                }
            }
        }
        return false;
    }

    private boolean executeCardAction(Card selectedCard) {
        try {
            int playerCountBefore = game.getNumberOfPlayers();
            selectedCard.playCard(game, ui);
            
            if (game.getNumberOfPlayers() < playerCountBefore) {
                handlePlayerElimination();
                return true;
            }
        } catch (Exception exception) {
            ui.displayMessage(exception.getMessage());
            ui.displayMessage("tryAgain");
        }
        return false;
    }

    private void handlePlayerElimination() {
        if (game.getNumberOfPlayers() == 1) {
            game.setGameOver(true);
        }
        ui.displayMessage("playerEliminated");
    }

    private void handleCardDrawingPhase() {
        Player currentPlayer = game.getCurrentPlayer();
        
        while (!currentPlayer.getIsTurnOver()) {
            if (game.isDeckEmpty()) {
                ui.displayMessage("deckEmpty");
                break;
            }
            
            Card cardDrawn = game.drawTopCard();
            
            if (isKittenCard(cardDrawn)) {
                cardDrawn.playCard(game, ui);
                break;
            } else {
                addDrawnCardToHand(cardDrawn);
                currentPlayer = game.getCurrentPlayer();
            }
        }
    }

    private boolean isKittenCard(Card card) {
        return card instanceof ExplodingKittenCard || card instanceof ImplodingKittenCard;
    }

    private void addDrawnCardToHand(Card card) {
        game.addToCurrentPlayer(card);
        ui.displayMessage("drawCard");
        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.decreaseTurnByOne();
        game.setPlayer(currentPlayer);
    }

    private void finalizeTurn() {
        if (game.getNumberOfPlayers() == 1) {
            game.setGameOver(true);
        }
        
        Player currentPlayer = game.getCurrentPlayer();
        ui.displayFormattedMessage("endTurn", currentPlayer.getId());
        resetPlayerState();
        game.nextPlayer();
    }

    private void resetPlayerState() {
        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.setNumberOfTurns(1);
        if (!currentPlayer.getHandVisibility()) {
            currentPlayer.setHandVisibility(true);
        }
        game.setPlayer(currentPlayer);
    }

    private void displayPlayerHand(Player player, Boolean visibility) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (visibility) {
                ui.displayFormattedMessage("visibleCard", i, player.getHand().get(i).getName());
            } else {
                ui.displayFormattedMessage("hiddenCard", i);
            }
        }
    }

    private void displayMarkCards() {
        var visibleCardsMap = game.getVisibleCards();

        if (!visibleCardsMap.isEmpty()) {
            ui.displayMessage("displayMarkedCards");
        }

        for (var entry : visibleCardsMap.entrySet()) {
            int playerId = entry.getKey();
            var visibleCards = entry.getValue();

            ui.displayFormattedMessage("playerIdMessage", playerId);

            for (Card card : visibleCards) {
                ui.displayFormattedMessage("cardDisplay", "-", card.getName());
            }
        }
    }

    private void displayGameInfo() {
        ui.displayMessage("activePlayers");
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            ui.displayFormattedMessage("playerInfo", player.getId());
        }
        ui.displayMessage("separator");
        int nextPlayerIndex = 1;
        if (players.size() > 1) {
            ui.displayFormattedMessage("nextPlayer", players.get(nextPlayerIndex).getId());
        }
        ui.displayMessage("separator");
    }
}
