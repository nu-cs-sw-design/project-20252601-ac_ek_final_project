package domain.model.cards.interaction;

import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

import java.util.InputMismatchException;

public class CatCard extends Card{
    private static final int[] COUNTS = {3, 4, 7};

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

    public CatCard(CatCard.CatCardType catCardType) {
        super(null);  // Will be set via getEffect()
        this.catCardType = catCardType.value;
    }

    @Override
    public CardEffect getEffect() {
        return new CatCardEffect(this);
    }

    @Override
    public String getName() {
        return CatCard.CatCardType.values()[catCardType].name;
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    private static class CatCardEffect implements CardEffect {
        private final CatCard card;

        CatCardEffect(CatCard card) {
            this.card = card;
        }

        @Override
        public void execute(GameContext context) {
            context.displayMessage("catCard");

            Player currentPlayer = context.getCurrentPlayer();
            String cardName = card.getName();
            boolean hasTwo = currentPlayer.hasTwoOf(cardName);
            if (!hasTwo) {
                throw new UnsupportedOperationException("needTwo");
            }

            int chosenPlayerIndex = context.promptPlayer("playerID");
            if (chosenPlayerIndex <= 0 || chosenPlayerIndex > context.getPlayers().size()) {
                throw new UnsupportedOperationException("invalidPlayerID");
            }
            if (currentPlayer.getId() == chosenPlayerIndex) {
                throw new InputMismatchException("chosenSelfError");
            }

            Player chosenPlayer = context.getPlayer(chosenPlayerIndex);
            int chosenCardIndex = context.promptPlayer("takeCard");
            Card chosenCard = chosenPlayer.chooseCard(chosenCardIndex);
            chosenPlayer.removeCard(chosenCardIndex);
            currentPlayer.addCard(chosenCard);

            int cardIndex = currentPlayer.hasCard(card.getName());
            context.removeCurrentPlayerCard(cardIndex);
            currentPlayer.removeCard(cardIndex);
            cardIndex = currentPlayer.hasCard(card.getName());
            context.removeCurrentPlayerCard(cardIndex);

            context.setPlayer(chosenPlayer);
            context.setPlayer(currentPlayer);
        }
    }
}