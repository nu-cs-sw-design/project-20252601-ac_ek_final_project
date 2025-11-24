package domain.model.cards.special;

import domain.model.Deck;
import domain.model.Game;
import domain.model.Player;
import domain.model.cards.Card;
import ui.UI;

public class ExplodingKittenCard extends Card {
    private static final int ADD_TO_DECK = 0;
    private static final int NOT_FOUND = -1;

    @Override
    public String getName() {
        return "Exploding Kitten";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("explodingKitten");
        Player currentPlayer = game.getCurrentPlayer();

        if (currentPlayer.hasCard("Defuse") != NOT_FOUND && currentPlayer.hasCard("Streaking Kitten") != NOT_FOUND) {
            int choice = ui.promptPlayer("keepOrAddExploding");

            if (choice == ADD_TO_DECK) {
                int defuseIndex = currentPlayer.hasCard("Defuse");
                game.removeCurrentPlayerCard(defuseIndex);
                insertIntoDeck(game, ui);
                ui.displayMessage("defuseCard");
            }
            else {
                game.addToCurrentPlayer(new ExplodingKittenCard());
                ui.displayMessage("addExplodingKitten");
            }
            return;
        }


        if (currentPlayer.hasCard("Defuse") != NOT_FOUND) {
            int defuseIndex = currentPlayer.hasCard("Defuse");
            game.removeCurrentPlayerCard(defuseIndex);
            ui.displayMessage("defuseCard");
            insertIntoDeck(game, ui);
            return;
        }

        if (currentPlayer.hasCard("Streaking Kitten") != NOT_FOUND) {
            game.addToCurrentPlayer(new ExplodingKittenCard());
            ui.displayMessage("addExplodingKitten");
            return;
        }

        int currentPlayerID = currentPlayer.getId();
        game.deletePlayer(currentPlayerID);
        ui.displayMessage("playerEliminated");
        int numPlayers = game.getPlayers().size();
        for (int i = 0; i < numPlayers - 1; i++) {
            game.nextPlayer();
        }
    }


    private void insertIntoDeck(Game game, UI ui) {
        Deck deck = game.getDeck();
        int insertIndex;
        boolean validIndex = false;
        
        while (!validIndex) {
            insertIndex = ui.promptPlayer("whereToInsert");
            try {
                deck.insertCardAtIndex(new ExplodingKittenCard(), insertIndex);
                game.setDeck(deck);
                ui.displayMessage("insertedCard");
                validIndex = true;
            } catch (IndexOutOfBoundsException e) {
                ui.displayMessage("indexOutOfBounds");
                ui.displayMessage("tryAgain");
            }
        }
    }
}