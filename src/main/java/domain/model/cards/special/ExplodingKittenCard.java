package domain.model.cards.special;

import domain.model.Deck;
import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

public class ExplodingKittenCard extends Card {
    private static final int ADD_TO_DECK = 0;
    private static final int NOT_FOUND = -1;

    public ExplodingKittenCard() {
        super(new ExplodingKittenCardEffect());
    }

    @Override
    public String getName() {
        return "Exploding Kitten";
    }

    private static class ExplodingKittenCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("explodingKitten");
            Player currentPlayer = context.getCurrentPlayer();
            boolean hasExplodingKitten = currentPlayer.hasCard("Exploding Kitten") != NOT_FOUND;

            if (currentPlayer.hasCard("Defuse") != NOT_FOUND && currentPlayer.hasCard("Streaking Kitten") != NOT_FOUND) {
                if (hasExplodingKitten) {
                    int defuseIndex = currentPlayer.hasCard("Defuse");
                    context.removeCurrentPlayerCard(defuseIndex);
                    insertIntoDeck(context);
                    context.displayMessage("defuseCard");
                    return;
                }

                int choice = context.promptPlayer("keepOrAddExploding");

                if (choice == ADD_TO_DECK) {
                    int defuseIndex = currentPlayer.hasCard("Defuse");
                    context.removeCurrentPlayerCard(defuseIndex);
                    insertIntoDeck(context);
                    context.displayMessage("defuseCard");
                }
                else {
                    context.addToCurrentPlayer(new ExplodingKittenCard());
                    context.displayMessage("addExplodingKitten");
                }
                return;
            }


            if (currentPlayer.hasCard("Defuse") != NOT_FOUND) {
                int defuseIndex = currentPlayer.hasCard("Defuse");
                context.removeCurrentPlayerCard(defuseIndex);
                context.displayMessage("defuseCard");
                insertIntoDeck(context);
                return;
            }

            if (currentPlayer.hasCard("Streaking Kitten") != NOT_FOUND && !hasExplodingKitten) {
                context.addToCurrentPlayer(new ExplodingKittenCard());
                context.displayMessage("addExplodingKitten");
                return;
            }

            int currentPlayerID = currentPlayer.getId();
            context.deletePlayer(currentPlayerID);
            context.displayMessage("playerEliminated");
            int numPlayers = context.getPlayers().size();
            for (int i = 0; i < numPlayers - 1; i++) {
                context.nextPlayer();
            }
        }

        private void insertIntoDeck(GameContext context) {
            Deck deck = context.getDeck();
            int insertIndex;
            boolean validIndex = false;
            
            while (!validIndex) {
                insertIndex = context.promptPlayer("whereToInsert");
                try {
                    deck.insertCardAtIndex(new ExplodingKittenCard(), insertIndex);
                    context.setDeck(deck);
                    context.displayMessage("insertedCard");
                    validIndex = true;
                } catch (IndexOutOfBoundsException e) {
                    context.displayMessage("indexOutOfBounds");
                    context.displayMessage("tryAgain");
                }
            }
        }
    }
}