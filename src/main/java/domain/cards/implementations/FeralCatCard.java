package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;
import domain.player.PlayerManager;

public class FeralCatCard extends Card {
    private static final int[] COUNTS = {4, 8, 12};

    public FeralCatCard() {
        super();
    }

    @Override
    public String getName() {
        return "Feral Cat";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("feralCatCard");

        Player currentPlayer = context.getCurrentPlayer();
        PlayerManager ps = context.getPlayerManager();
        boolean hasCatCard = false;
        Card catCard = null;

        for (CatCard.CatCardType catCardType : CatCard.CatCardType.values()) {
            if (ps.hasCard(currentPlayer, catCardType.cardName()) != -1) {
                hasCatCard = true;
                catCard = new CatCard(catCardType);
                break;
            }
        }

        if (!hasCatCard) {
            boolean hasFeralCat = ps.hasTwoOf(currentPlayer, "Feral Cat");
            if (!hasFeralCat) {
                throw new UnsupportedOperationException("needTwo");
            }
        }

        CardManager cm = context.getCardManager();
        Player chosenPlayer = cm.chooseValidTargetPlayer(context, "playerID");
        cm.takeCardFromPlayer(context, chosenPlayer);

        if (hasCatCard) {
            cm.removeCardFromCurrentPlayer(context, catCard.getName());
        } else {
            cm.removeCardFromCurrentPlayer(context, "Feral Cat");
        }
        cm.removeCardFromCurrentPlayer(context, "Feral Cat");
    }
}