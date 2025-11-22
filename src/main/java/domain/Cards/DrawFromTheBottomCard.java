package domain.Cards;

import domain.Deck;
import domain.Game;
import domain.Player;
import ui.UI;

public class DrawFromTheBottomCard extends Card {
    private static final int[] COUNTS = {6, 8, 10};

    @Override
    public String getName() {
        return "Draw From The Bottom";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("drawFromBottomCard");

        Deck deck = game.getDeck();
        Card bottomCard = deck.drawBottomCard();
        if (bottomCard instanceof ImplodingKittenCard || bottomCard instanceof ExplodingKittenCard) {
            bottomCard.playCard(game, ui);
        } else {
            game.addToCurrentPlayer(bottomCard);
        }
        
        Player currPlayer = game.getCurrentPlayer();
        int currentTurns = currPlayer.getNumberOfTurns();
        if (currentTurns > 0) {
            game.setCurrentPlayerTurns(currentTurns - 1);
        }
        game.setDeck(deck);

        int cardIndex = currPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }
}