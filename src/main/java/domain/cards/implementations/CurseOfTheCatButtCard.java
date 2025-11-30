package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;
import domain.player.PlayerManager;

public class CurseOfTheCatButtCard extends Card {
    private static final int[] COUNTS = {2, 3, 4};

    public CurseOfTheCatButtCard() {
        super();
    }

    @Override
    public String getName() {
        return "Curse of the Cat Butt";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("curseCatButt");

        CardManager cm = context.getCardManager();
        Player chosenPlayer = cm.chooseValidTargetPlayer(context, "playerIDCurse");
        PlayerManager ps = context.getPlayerManager();
        chosenPlayer.setHandVisibility(false);
        ps.shuffleHand(chosenPlayer);
        context.setPlayer(chosenPlayer);
        cm.removeCardFromCurrentPlayer(context, "Curse of the Cat Butt");
    }
}