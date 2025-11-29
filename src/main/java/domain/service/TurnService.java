package domain.service;

import domain.model.cards.Card;
import domain.model.Game;
import domain.model.Player;
import domain.model.AIAgent;
import domain.model.cards.special.ExplodingKittenCard;
import domain.model.cards.special.ImplodingKittenCard;

import java.util.List;

public class TurnService {
    private final Game game;
    private final AIAgent aiAgent = new AIAgent();

    public TurnService(Game game) {
        this.game = game;
    }

    public void executeTurn() {
        initializeTurn();
        handleCardPlayingPhase();
        handleCardDrawingPhase();
        finalizeTurn();
    }

    private int prompt(Player player, String key) {
        if (player.isAI()) {
            return aiAgent.getDecision(key, game);
        }
        return game.getInputProvider().promptPlayer(key);
    }

    private void initializeTurn() {
        game.notifyClearScreen();
        displayGameInfo();
        game.notifyFormattedMessage("deckSize", game.getDeckSize());
        Player currentPlayer = game.getCurrentPlayer();
        game.notifyFormattedMessage("player", currentPlayer.getId());
        game.notifyMessage("turnStart");
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
            game.notifyMessage("currentHand");
            displayPlayerHand(currentPlayer, currentPlayer.getHandVisibility());
            playCardChoice = prompt(currentPlayer, "playCardPrompt");

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
            int cardIndex = prompt(currentPlayer, "chooseCardPrompt");
            
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
                game.notifyMessage("invalidCardIndex");
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
                    game.notifyFormattedMessage("player", player.getId());
                    int wantsToPlay = prompt(player, "chooseNope");
                    
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
        nopeCard.playCard(game);
        game.notifyMessage("playedNope");
        
        boolean nopeWasNoped = checkForCounterNope(nopingPlayer);
        
        return !nopeWasNoped;
    }

    private boolean checkForCounterNope(Player lastNopingPlayer) {
        List<Player> players = game.getPlayers();
        
        for (Player player : players) {
            if (player.getId() != lastNopingPlayer.getId()) {
                int nopeIndex = player.hasCard("Nope");
                if (nopeIndex != -1) {
                    game.notifyFormattedMessage("player", player.getId());
                    int wantsToPlay = prompt(player, "chooseNope");
                    
                    if (wantsToPlay == 1) {
                        Card counterNope = player.chooseCard(nopeIndex);
                        player.removeCard(nopeIndex);
                        game.setPlayer(player);
                        counterNope.playCard(game);
                        game.notifyMessage("playedNope");
                        
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
            selectedCard.playCard(game);
            
            if (game.getNumberOfPlayers() < playerCountBefore) {
                handlePlayerElimination();
                return true;
            }
        } catch (Exception exception) {
            game.notifyMessage(exception.getMessage());
            game.notifyMessage("tryAgain");
        }
        return false;
    }

    private void handlePlayerElimination() {
        if (game.getNumberOfPlayers() == 1) {
            game.setGameOver(true);
        }
        game.notifyMessage("playerEliminated");
    }

    private void handleCardDrawingPhase() {
        Player currentPlayer = game.getCurrentPlayer();
        
        while (!currentPlayer.getIsTurnOver()) {
            if (game.isDeckEmpty()) {
                game.notifyMessage("deckEmpty");
                break;
            }
            
            Card cardDrawn = game.drawTopCard();
            
            if (isKittenCard(cardDrawn)) {
                cardDrawn.playCard(game);
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
        game.notifyMessage("drawCard");
        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.decreaseTurnByOne();
        game.setPlayer(currentPlayer);
    }

    private void finalizeTurn() {
        if (game.getNumberOfPlayers() == 1) {
            game.setGameOver(true);
        }
        
        Player currentPlayer = game.getCurrentPlayer();
        game.notifyFormattedMessage("endTurn", currentPlayer.getId());
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
                game.notifyFormattedMessage("visibleCard", i, player.getHand().get(i).getName());
            } else {
                game.notifyFormattedMessage("hiddenCard", i);
            }
        }
    }

    private void displayMarkCards() {
        var visibleCardsMap = game.getVisibleCards();

        if (!visibleCardsMap.isEmpty()) {
            game.notifyMessage("displayMarkedCards");
        }

        for (var entry : visibleCardsMap.entrySet()) {
            int playerId = entry.getKey();
            var visibleCards = entry.getValue();

            game.notifyFormattedMessage("playerIdMessage", playerId);

            for (Card card : visibleCards) {
                game.notifyFormattedMessage("cardDisplay", "-", card.getName());
            }
        }
    }

    private void displayGameInfo() {
        game.notifyMessage("activePlayers");
        List<Player> players = game.getPlayers();
        for (Player player : players) {
            game.notifyFormattedMessage("playerInfo", player.getId());
        }
        game.notifyMessage("separator");
        int nextPlayerIndex = 1;
        if (players.size() > 1) {
            game.notifyFormattedMessage("nextPlayer", players.get(nextPlayerIndex).getId());
        }
        game.notifyMessage("separator");
    }
}
