package domain.Cards;

import domain.Deck;
import domain.Game;
import ui.UI;


public class ImplodingKittenCard extends Card {
    private final int drawnBefore;

    public enum DrawnBefore {
        NOT_DRAWN(0),
        DRAWN(1);

        private final int value;

        DrawnBefore(int value) {
            this.value = value;
        }
    }

    public ImplodingKittenCard(DrawnBefore drawnBefore) {
        this.drawnBefore = drawnBefore.value;
    }

    @Override
    public String getName() {
        return "Imploding Kitten";
    }

    @Override
    public void playCard(Game game, UI ui) {
        if (drawnBefore == DrawnBefore.NOT_DRAWN.value) {
            ui.displayMessage("implodingKittenFaceDown");

            Deck deck = game.getDeck();
            ui.displayFormattedMessage("ImplodingKittenIndexHelp", deck.numberOfCards());
            int insertImplodingKittenAtIndex = ui.promptPlayer("whereToInsert");
            deck.insertCardAtIndex(new ImplodingKittenCard(ImplodingKittenCard.DrawnBefore.DRAWN), insertImplodingKittenAtIndex);
            game.setDeck(deck);
        }
        else {
            ui.displayMessage("implodingKittenFaceUp");
            int currentPlayerID = game.getCurrentPlayer().getId();
            game.deletePlayer(currentPlayerID);
            ui.displayMessage("playerEliminated");
            int numPlayers = game.getPlayers().size();
            for (int i = 0; i < numPlayers - 1; i++) {
                game.nextPlayer();
            }
        }
    }
}