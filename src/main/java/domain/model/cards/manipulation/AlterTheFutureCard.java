package domain.model.cards.manipulation;

import domain.model.Deck;
import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

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
        super(null);
        this.peekOption = peekOption.value;
    }

    @Override
    public CardEffect getEffect() {
        return new AlterTheFutureCardEffect(this);
    }

    @Override
    public String getName() {
        return "Alter The Future";
    }

    public void setIndexes(List<Integer> newIndexes) {
        indexes = new ArrayList<>(newIndexes);
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    private static class AlterTheFutureCardEffect implements CardEffect {
        private final AlterTheFutureCard card;

        AlterTheFutureCardEffect(AlterTheFutureCard card) {
            this.card = card;
        }

        @Override
        public void execute(GameContext context) {
            context.displayMessage("alterTheFutureCard");

            Deck deck = context.getDeck();
            List<Card> cardsToAlter = new ArrayList<>(deck.peekTopDeck(card.peekOption));

            for (int i = 0; i < cardsToAlter.size(); i++) {
                context.displayFormattedMessage("visibleCard", i, cardsToAlter.get(i).getName());
            }

            List<Integer> newIndexes = new ArrayList<>();
            int maxIndex = cardsToAlter.size() - 1;

            for (int i = 0; i < cardsToAlter.size(); i++) {
                context.displayFormattedMessage("cardAtIndex", i);
                boolean validInput = false;
                while (!validInput) {
                    int newIndex = context.promptPlayer("newIndex");
                    if (newIndex >= 0 && newIndex <= maxIndex) {
                        newIndexes.add(newIndex);
                        validInput = true;
                    } else {
                        context.displayMessage("indexOutOfBounds");
                        context.displayMessage("tryAgain");
                    }
                }
            }

            card.setIndexes(newIndexes);

            boolean validIndexes = false;
            while (!validIndexes) {
                try {
                    deck.alterTopDeck(cardsToAlter, card.indexes);
                    context.setDeck(deck);
                    validIndexes = true;
                } catch (IllegalArgumentException e) {
                    context.displayMessage(e.getMessage());
                    context.displayMessage("tryAgain");
                    newIndexes.clear();
                    for (int i = 0; i < cardsToAlter.size(); i++) {
                        context.displayFormattedMessage("cardAtIndex", i);
                        boolean validInput = false;
                        while (!validInput) {
                            int newIndex = context.promptPlayer("newIndex");
                            if (newIndex >= 0 && newIndex <= maxIndex) {
                                newIndexes.add(newIndex);
                                validInput = true;
                            } else {
                                context.displayMessage("indexOutOfBounds");
                                context.displayMessage("tryAgain");
                            }
                        }
                    }
                    card.setIndexes(newIndexes);
                }
            }

            Player currentPlayer = context.getCurrentPlayer();
            int cardIndex = currentPlayer.hasCard("Alter The Future");
            context.removeCurrentPlayerCard(cardIndex);
        }
    }
}