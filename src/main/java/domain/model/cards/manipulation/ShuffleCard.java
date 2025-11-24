package domain.model.cards.manipulation;

import domain.model.Deck;
import domain.model.Game;
import domain.model.Player;
import domain.model.cards.Card;
import ui.UI;

public class ShuffleCard extends Card{
    private static final int[] COUNTS = {2, 4, 6};

    @Override
    public String getName() {
        return "Shuffle";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("shuffleCard");

        Deck deck = game.getDeck();
        deck.shuffle();
        game.setDeck(deck);

        Player currentPlayer = game.getCurrentPlayer();
        int cardIndex = currentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }
}