package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.game.GameContext;
import domain.player.Player;

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
        super();
        this.drawnBefore = drawnBefore.value;
    }

    @Override
    public String getName() {
        return "Imploding Kitten";
    }

    @Override
    public void executeEffect(GameContext context) {
        CardManager cm = context.getCardManager();

        if (drawnBefore == DrawnBefore.NOT_DRAWN.value) {
            context.displayMessage("implodingKittenFaceDown");

            Deck deck = context.getDeck();
            context.displayFormattedMessage("ImplodingKittenIndexHelp", deck.numberOfCards());
            cm.insertCardAtValidIndex(context, new ImplodingKittenCard(DrawnBefore.DRAWN));
        } else {
            context.displayMessage("implodingKittenFaceUp");
            
            Player currentPlayer = context.getCurrentPlayer();
            cm.eliminatePlayer(context, currentPlayer);
        }
    }
}