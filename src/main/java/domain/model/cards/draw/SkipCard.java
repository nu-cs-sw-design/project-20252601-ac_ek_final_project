package domain.model.cards.draw;

import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

public class SkipCard extends Card {
    private static final int[] COUNTS = {4, 6, 10};

    public SkipCard() {
        super(new SkipCardEffect());
    }

    @Override
    public String getName() {
        return "Skip";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    private static class SkipCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("skipCard");

            Player currPlayer = context.getCurrentPlayer();
            int turns = currPlayer.getNumberOfTurns();
            if (turns > 0) {
                context.setCurrentPlayerTurns(turns - 1);
            }

            int cardIndex = currPlayer.hasCard("Skip");
            context.removeCurrentPlayerCard(cardIndex);
        }
    }
}