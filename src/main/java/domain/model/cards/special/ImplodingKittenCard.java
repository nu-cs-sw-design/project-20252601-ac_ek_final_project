package domain.model.cards.special;

import domain.model.Deck;
import domain.model.GameContext;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;


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
        super(null);
        this.drawnBefore = drawnBefore.value;
    }

    @Override
    public CardEffect getEffect() {
        return new ImplodingKittenCardEffect(this);
    }

    @Override
    public String getName() {
        return "Imploding Kitten";
    }

    private static class ImplodingKittenCardEffect implements CardEffect {
        private final ImplodingKittenCard card;

        ImplodingKittenCardEffect(ImplodingKittenCard card) {
            this.card = card;
        }

        @Override
        public void execute(GameContext context) {
            if (card.drawnBefore == DrawnBefore.NOT_DRAWN.value) {
                context.displayMessage("implodingKittenFaceDown");

                Deck deck = context.getDeck();
                context.displayFormattedMessage("ImplodingKittenIndexHelp", deck.numberOfCards());
                boolean validIndex = false;
                
                while (!validIndex) {
                    int insertImplodingKittenAtIndex = context.promptPlayer("whereToInsert");
                    try {
                        deck.insertCardAtIndex(new ImplodingKittenCard(ImplodingKittenCard.DrawnBefore.DRAWN), insertImplodingKittenAtIndex);
                        context.setDeck(deck);
                        validIndex = true;
                    } catch (IndexOutOfBoundsException e) {
                        context.displayMessage("indexOutOfBounds");
                        context.displayMessage("tryAgain");
                    }
                }
            }
            else {
                context.displayMessage("implodingKittenFaceUp");
                int currentPlayerID = context.getCurrentPlayer().getId();
                context.deletePlayer(currentPlayerID);
                context.displayMessage("playerEliminated");
                int numPlayers = context.getPlayers().size();
                for (int i = 0; i < numPlayers - 1; i++) {
                    context.nextPlayer();
                }
            }
        }
    }
}