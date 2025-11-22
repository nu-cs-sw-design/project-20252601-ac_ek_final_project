package domain.Cards;

import domain.Deck;
import domain.Game;
import domain.Player;
import ui.UI;

import java.util.ArrayList;
import java.util.List;

public class AlterTheFutureCard extends Card{
    private static final int[] COUNTS = {5, 9, 11};

    private final int peekOption;
    private List<Integer> indexes = new ArrayList<>();

    public enum PeekOption {
        THREE(3),
        FIVE(5);

        private final int value;

        PeekOption(int value) {
            this.value = value;
        }
    }

    public AlterTheFutureCard(PeekOption peekOption) {
        this.peekOption = peekOption.value;
    }

    @Override
    public String getName() {
        return "Alter The Future";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("alterTheFutureCard");

        Deck deck = game.getDeck();
        List<Card> cardsToAlter = new ArrayList<>(deck.peekTopDeck(peekOption));

        for (int i = 0; i < cardsToAlter.size(); i++) {
            ui.displayFormattedMessage("visibleCard", i, cardsToAlter.get(i).getName());
        }

        List<Integer> newIndexes = new ArrayList<>();
        int maxIndex = cardsToAlter.size() - 1;

        for (int i = 0; i < cardsToAlter.size(); i++) {
            ui.displayFormattedMessage("cardAtIndex", i);
            boolean validInput = false;
            while (!validInput) {
                int newIndex = ui.promptPlayer("newIndex");
                if (newIndex >= 0 && newIndex <= maxIndex) {
                    newIndexes.add(newIndex);
                    validInput = true;
                } else {
                    ui.displayMessage("indexOutOfBounds");
                    ui.displayMessage("tryAgain");
                }
            }
        }

        setIndexes(newIndexes);

        boolean validIndexes = false;
        while (!validIndexes) {
            try {
                deck.alterTopDeck(cardsToAlter, indexes);
                game.setDeck(deck);
                validIndexes = true;
            } catch (IllegalArgumentException e) {
                ui.displayMessage(e.getMessage());
                ui.displayMessage("tryAgain");
                newIndexes.clear();
                for (int i = 0; i < cardsToAlter.size(); i++) {
                    ui.displayFormattedMessage("cardAtIndex", i);
                    boolean validInput = false;
                    while (!validInput) {
                        int newIndex = ui.promptPlayer("newIndex");
                        if (newIndex >= 0 && newIndex <= maxIndex) {
                            newIndexes.add(newIndex);
                            validInput = true;
                        } else {
                            ui.displayMessage("indexOutOfBounds");
                            ui.displayMessage("tryAgain");
                        }
                    }
                }
                setIndexes(newIndexes);
            }
        }

        Player currentPlayer = game.getCurrentPlayer();
        int cardIndex = currentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);
    }


    public void setIndexes(List<Integer> newIndexes) {
        indexes = new ArrayList<>(newIndexes);
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }
}