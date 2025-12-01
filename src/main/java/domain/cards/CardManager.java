package domain.cards;

import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.game.GameContext;
import domain.player.Player;
import domain.player.PlayerManager;

public class CardManager {

    public CardManager() {
    }

    public CardManager(CardManager other) {
    }

    public Player chooseValidTargetPlayer(GameContext context, String promptKey) {
        Player currentPlayer = context.getCurrentPlayer();
        Player chosenPlayer = null;

        while (chosenPlayer == null) {
            int chosenPlayerId = context.promptPlayer(promptKey);
            if (chosenPlayerId == currentPlayer.getId()) {
                context.displayMessage("chosenSelfError");
                context.displayMessage("tryAgain");
                continue;
            }
            try {
                chosenPlayer = context.getPlayer(chosenPlayerId);
                if (chosenPlayer == null) {
                    context.displayMessage("invalidPlayerID");
                    context.displayMessage("tryAgain");
                }
            } catch (Exception e) {
                context.displayMessage("invalidPlayerID");
                context.displayMessage("tryAgain");
            }
        }
        return chosenPlayer;
    }

    public void removeCardFromCurrentPlayer(GameContext context, String cardName) {
        Player currentPlayer = context.getCurrentPlayer();
        PlayerManager ps = context.getPlayerManager();
        int cardIndex = ps.hasCard(currentPlayer, cardName);
        context.removeCurrentPlayerCard(cardIndex);
    }

    public void eliminatePlayer(GameContext context, Player player) {
        context.deletePlayer(player.getId());
        context.displayMessage("playerEliminated");
        int numPlayers = context.getPlayers().size();
        for (int i = 0; i < numPlayers - 1; i++) {
            context.nextPlayer();
        }
    }

    public void insertCardAtValidIndex(GameContext context, Card card) {
        Deck deck = context.getDeck();
        DeckManager dm = context.getDeckManager();
        boolean validIndex = false;

        while (!validIndex) {
            int insertIndex = context.promptPlayer("whereToInsert");
            try {
                dm.insertCardAtIndex(deck, card, insertIndex);
                context.setDeck(deck);
                context.displayMessage("insertedCard");
                validIndex = true;
            } catch (IndexOutOfBoundsException e) {
                context.displayMessage("indexOutOfBounds");
                context.displayMessage("tryAgain");
            }
        }
    }

    public Card takeCardFromPlayer(GameContext context, Player chosenPlayer) {
        PlayerManager ps = context.getPlayerManager();
        Player currentPlayer = context.getCurrentPlayer();

        int chosenCardIndex = context.promptPlayer("takeCard");
        Card chosenCard = ps.chooseCard(chosenPlayer, chosenCardIndex);
        ps.removeCard(chosenPlayer, chosenCardIndex);
        ps.addCard(currentPlayer, chosenCard);
        context.setPlayer(chosenPlayer);
        context.setPlayer(currentPlayer);

        return chosenCard;
    }

    public int calculateAttackTurns(int currentPlayerTurns, int turnsToAdd) {
        if (currentPlayerTurns == 1) {
            return turnsToAdd;
        }
        return currentPlayerTurns + turnsToAdd;
    }

    public java.util.List<Integer> collectUniqueIndexes(GameContext context, int count) {
        int maxIndex = count - 1;

        while (true) {
            java.util.List<Integer> indexes = new java.util.ArrayList<>();

            for (int i = 0; i < count; i++) {
                context.displayFormattedMessage("cardAtIndex", i);
                boolean validInput = false;
                while (!validInput) {
                    int newIndex = context.promptPlayer("newIndex:" + count);
                    if (newIndex >= 0 && newIndex <= maxIndex) {
                        indexes.add(newIndex);
                        validInput = true;
                    } else {
                        context.displayMessage("indexOutOfBounds");
                        context.displayMessage("tryAgain");
                    }
                }
            }

            if (hasUniqueIndexes(indexes)) {
                return indexes;
            } else {
                context.displayMessage("newIndexesUnique");
                context.displayMessage("tryAgain");
            }
        }
    }

    private boolean hasUniqueIndexes(java.util.List<Integer> indexes) {
        return indexes.size() == new java.util.HashSet<>(indexes).size();
    }
}