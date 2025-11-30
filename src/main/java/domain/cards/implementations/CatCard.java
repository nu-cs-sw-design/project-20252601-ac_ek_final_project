package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;
import domain.player.PlayerManager;

public class CatCard extends Card {
    private static final int[] COUNTS = {4, 6, 8};

    private final int catCardType;

    public enum CatCardType {
        BEARDED_CAT(0, "Bearded Cat"),
        HAIRY_POTATO_CAT(1, "Hairy Potato Cat"),
        RAINBOW_RALPHING_CAT(2, "Rainbow Ralphing Cat"),
        TACO_CAT(3, "Taco Cat"),
        CATTERMELON(4, "Cattermelon");

        private final int value;
        private final String name;

        CatCardType(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public String cardName() {
            return name;
        }
    }

    public CatCard(CatCardType catCardType) {
        super();
        this.catCardType = catCardType.value;
    }

    @Override
    public String getName() {
        return CatCardType.values()[catCardType].name;
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("catCard");

        Player currentPlayer = context.getCurrentPlayer();
        PlayerManager ps = context.getPlayerManager();
        String cardName = getName();
        boolean hasTwo = ps.hasTwoOf(currentPlayer, cardName);
        if (!hasTwo) {
            throw new UnsupportedOperationException("needTwo");
        }

        CardManager cm = context.getCardManager();
        Player chosenPlayer = cm.chooseValidTargetPlayer(context, "playerID");
        cm.takeCardFromPlayer(context, chosenPlayer);
        cm.removeCardFromCurrentPlayer(context, cardName);
        cm.removeCardFromCurrentPlayer(context, cardName);
    }
}