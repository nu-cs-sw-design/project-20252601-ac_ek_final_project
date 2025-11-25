package domain.model.cards.interaction;

import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

import java.util.InputMismatchException;

public class FeralCatCard extends Card{
    private static final int[] COUNTS = {4, 8, 12};

    public FeralCatCard() {
        super(new FeralCatCardEffect());
    }

    @Override
    public String getName() {
        return "Feral Cat";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    private static class FeralCatCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("feralCatCard");

            Player currentPlayer = context.getCurrentPlayer();
            boolean hasCatCard = false;
            Card catCard = null;

            for (CatCard.CatCardType catCardType : CatCard.CatCardType.values()) {
                if (currentPlayer.hasCard(catCardType.cardName()) != -1) {
                    hasCatCard = true;
                    catCard = new CatCard(catCardType);
                    break;
                }
            }

            if (!hasCatCard) {
                boolean hasFeralCat = currentPlayer.hasTwoOf("Feral Cat");
                if (!hasFeralCat) {
                    throw new UnsupportedOperationException("needTwo");
                }
            }

            int chosenPlayerIndex = context.promptPlayer("playerID");
            Player chosenPlayer = context.getPlayer(chosenPlayerIndex);
            if (chosenPlayerIndex == currentPlayer.getId()) {
                throw new InputMismatchException("chosenSelfError");
            }

            int chosenCardIndex = context.promptPlayer("takeCard");
            Card chosenCard = chosenPlayer.chooseCard(chosenCardIndex);
            chosenPlayer.removeCard(chosenCardIndex);
            currentPlayer.addCard(chosenCard);
            context.setPlayer(chosenPlayer);
            context.setPlayer(currentPlayer);

            int cardIndex;
            if (hasCatCard) {
                cardIndex = currentPlayer.hasCard(catCard.getName());
            } else {
                cardIndex = currentPlayer.hasCard("Feral Cat");
            }
            context.removeCurrentPlayerCard(cardIndex);
            int cardIndexSecondCard = currentPlayer.hasCard("Feral Cat");
            context.removeCurrentPlayerCard(cardIndexSecondCard);
        }
    }
}