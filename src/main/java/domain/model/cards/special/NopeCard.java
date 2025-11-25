package domain.model.cards.special;

import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

public class NopeCard extends Card {
    private static final int[] COUNTS = {4, 5, 9};

    public NopeCard() {
        super(new NopeCardEffect());
    }

    @Override
    public String getName() {
        return "Nope";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    private static class NopeCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            Player currentPlayer = context.getCurrentPlayer();
            context.setCurrentPlayerHasNope(currentPlayer.hasTwoOf("Nope"));
        }
    }
}
