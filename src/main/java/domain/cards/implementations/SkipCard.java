package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;

public class SkipCard extends Card {
    private static final int[] COUNTS = {4, 6, 10};

    public SkipCard() {
        super();
    }

    @Override
    public String getName() {
        return "Skip";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("skipCard");

        Player currPlayer = context.getCurrentPlayer();
        int turns = currPlayer.getNumberOfTurns();
        if (turns > 0) {
            context.setCurrentPlayerTurns(turns - 1);
        }
        CardManager cm = context.getCardManager();
        cm.removeCardFromCurrentPlayer(context, "Skip");
    }
}