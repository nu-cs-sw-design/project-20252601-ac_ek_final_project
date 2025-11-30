package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;

public class SuperSkipCard extends Card {
    private static final int END_TURN = 0;

    public SuperSkipCard() {
        super();
    }

    @Override
    public String getName() {
        return "Super Skip";
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("superSkipCard");

        Player currPlayer = context.getCurrentPlayer();
        if (currPlayer.getNumberOfTurns() > 0) {
            context.setCurrentPlayerTurns(END_TURN);
        }
        CardManager cm = context.getCardManager();
        cm.removeCardFromCurrentPlayer(context, "Super Skip");
    }
}