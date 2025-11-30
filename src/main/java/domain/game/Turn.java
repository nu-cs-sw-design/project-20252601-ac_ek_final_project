package domain.game;

import domain.cards.Card;
import domain.cards.implementations.CatCard;
import domain.cards.implementations.ExplodingKittenCard;
import domain.cards.implementations.FeralCatCard;
import domain.cards.implementations.ImplodingKittenCard;
import domain.player.Player;
import domain.player.PlayerManager;

import java.util.List;

public class Turn {
    private final GameEngine game;
    private final NopeOperation nopeOperation;
    private final PlayerManager PlayerManager = new PlayerManager();

    public Turn(GameEngine game, NopeOperation nopeOperation) {
        this.game = game;
        this.nopeOperation = nopeOperation;
    }

    public void executeTurn() {
        initializeTurn();
        handleCardPlayingPhase();
        handleCardDrawingPhase();
        finalizeTurn();
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
        if (PlayerManager.isHandEmpty(currentPlayer)) {
            return;
        }

        boolean turnEnded = false;
        while (!turnEnded && currentPlayer.getNumberOfTurns() > 0 && !PlayerManager.isHandEmpty(currentPlayer)) {
            game.notifyMessage("currentHand");
            displayPlayerHand(currentPlayer, currentPlayer.getHandVisibility());
            
            Action action = currentPlayer.getController().getTurnAction(game);
            
            if (action.getType() == Action.Type.PASS) {
                turnEnded = true;
            } else if (action.getType() == Action.Type.PLAY_CARD) {
                boolean playerEliminated = attemptToPlayCard(action.getCardIndex());
                if (playerEliminated) {
                    return;
                }
                currentPlayer = game.getCurrentPlayer();
            }
        }
    }

    private boolean attemptToPlayCard(int cardIndex) {
        Player currentPlayer = game.getCurrentPlayer();
        
        try {
            Card selectedCard = PlayerManager.chooseCard(currentPlayer, cardIndex);
            boolean actionCanceled = nopeOperation.checkForNopeInterruption(game, selectedCard);

            if (!actionCanceled) {
                boolean playerEliminated = executeCardAction(selectedCard);
                
                if (playerEliminated) {
                    return true;
                }
            } else {
                currentPlayer = game.getCurrentPlayer();
                PlayerManager.removeCard(currentPlayer, cardIndex);
                
                if (selectedCard instanceof CatCard || selectedCard instanceof FeralCatCard) {
                    removeSecondCatCard(currentPlayer, selectedCard);
                }
                
                game.setPlayer(currentPlayer);
            }
        } catch (IndexOutOfBoundsException e) {
            game.notifyMessage("invalidCardIndex");
        }
        
        return false;
    }

    private void removeSecondCatCard(Player player, Card selectedCard) {
        String cardName = selectedCard.getName();
        int secondCardIndex = PlayerManager.hasCard(player, cardName);
        
        if (secondCardIndex != -1) {
            PlayerManager.removeCard(player, secondCardIndex);
        } else if (selectedCard instanceof FeralCatCard) {
            for (CatCard.CatCardType catType : CatCard.CatCardType.values()) {
                int catIndex = PlayerManager.hasCard(player, catType.cardName());
                if (catIndex != -1) {
                    PlayerManager.removeCard(player, catIndex);
                    break;
                }
            }
        }
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
        
        while (!PlayerManager.isTurnOver(currentPlayer)) {
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
        PlayerManager.decreaseTurnByOne(currentPlayer);
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