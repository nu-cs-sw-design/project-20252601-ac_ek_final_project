package domain.cards.implementations;

import domain.cards.Card;
import domain.game.GameContext;

public class NopeCard extends Card {
    private static final int[] COUNTS = {5, 7, 10};

    public NopeCard() {
        super();
    }

    @Override
    public String getName() {
        return "Nope";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("nopeCard");
    }
}