package domain.Cards;

import domain.Deck;
import domain.Game;
import domain.Player;
import ui.UI;

import java.util.List;

public class SeeTheFutureCard extends Card {
    private static final int[] COUNTS = {4, 4, 7};

    private final int peekNumCards;
    private List<Card> peekedCards;

    public enum PeekOption {
        THREE(3), FIVE(5);

        private final int value;

        PeekOption(int value) {
            this.value = value;
        }
    }

    public SeeTheFutureCard(PeekOption peekOption) {
        this.peekNumCards = peekOption.value;
    }

    @Override
    public String getName() {
        return "See The Future";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("seeTheFutureCard");

        playCard(game, peekNumCards);
        displayCards(ui);

        Player currentPlayer = game.getCurrentPlayer();
        int cardIndex = currentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);
    }

    private void playCard(Game game, int numCardsToSee) {
        Deck deck = game.getDeck();
        this.peekedCards = deck.peekTopDeck(numCardsToSee);
    }

    private void displayCards(UI ui) {
        for (int i = 0; i < peekedCards.size(); i++) {
            ui.displayFormattedMessage("visibleCard", i, peekedCards.get(i).getName());
        }
    }

    public List<Card> getPeekedCards() {
        return List.copyOf(peekedCards);
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }
}