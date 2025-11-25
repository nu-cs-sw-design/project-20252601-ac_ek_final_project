package domain.model.cards.manipulation;

import domain.model.Deck;
import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

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
        super(null);  // Will be set in constructor body
        this.peekNumCards = peekOption.value;
    }

    @Override
    public CardEffect getEffect() {
        return new SeeTheFutureCardEffect(this);
    }

    @Override
    public String getName() {
        return "See The Future";
    }

    private void setPeekedCards(List<Card> cards) {
        this.peekedCards = cards;
    }

    private void displayCards(GameContext context) {
        for (int i = 0; i < peekedCards.size(); i++) {
            context.displayFormattedMessage("visibleCard", i, peekedCards.get(i).getName());
        }
    }

    public List<Card> getPeekedCards() {
        return List.copyOf(peekedCards);
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    private static class SeeTheFutureCardEffect implements CardEffect {
        private final SeeTheFutureCard card;

        SeeTheFutureCardEffect(SeeTheFutureCard card) {
            this.card = card;
        }

        @Override
        public void execute(GameContext context) {
            context.displayMessage("seeTheFutureCard");

            Deck deck = context.getDeck();
            card.setPeekedCards(deck.peekTopDeck(card.peekNumCards));
            card.displayCards(context);

            Player currentPlayer = context.getCurrentPlayer();
            int cardIndex = currentPlayer.hasCard("See The Future");
            context.removeCurrentPlayerCard(cardIndex);
        }
    }
}