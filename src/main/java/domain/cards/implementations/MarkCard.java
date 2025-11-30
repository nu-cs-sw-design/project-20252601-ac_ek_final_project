package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;
import domain.player.PlayerManager;

public class MarkCard extends Card {
    private static final int[] COUNTS = {3, 4, 5};

    public MarkCard() {
        super();
    }

    @Override
    public String getName() {
        return "Mark";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("markCard");

        CardManager cm = context.getCardManager();
        Player targetPlayer = cm.chooseValidTargetPlayer(context, "targetPlayerId");
        PlayerManager ps = context.getPlayerManager();
        int targetCardIndex = context.promptPlayer("targetCardIndex");
        if (targetCardIndex < 0 || targetCardIndex >= ps.getHandCount(targetPlayer)) {
            throw new UnsupportedOperationException("invalidCardIndex");
        }
        Card targetCard = ps.chooseCard(targetPlayer, targetCardIndex);
        if (ps.getVisibleHand(targetPlayer).contains(targetCard)) {
            throw new UnsupportedOperationException("alreadyVisible");
        }
        ps.addVisibleCard(targetPlayer, targetCard);
        context.setPlayer(targetPlayer);
        cm.removeCardFromCurrentPlayer(context, "Mark");
    }
}