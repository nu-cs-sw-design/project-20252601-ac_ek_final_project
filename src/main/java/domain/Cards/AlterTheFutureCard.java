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

        for (int i = 0; i < cardsToAlter.size(); i++) {
            ui.displayFormattedMessage("cardAtIndex", i);
            int newIndex = ui.promptPlayer("newIndex");
            newIndexes.add(newIndex);
        }

        setIndexes(newIndexes);

        deck.alterTopDeck(cardsToAlter, indexes);
        game.setDeck(deck);

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