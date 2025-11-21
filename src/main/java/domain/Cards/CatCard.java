package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

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
        this.catCardType = catCardType.value;
    }

    @Override
    public String getName() {
        return CatCard.CatCardType.values()[catCardType].name;
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("catCard");

        Player currentPlayer = game.getCurrentPlayer();
        String cardName = this.getName();
        boolean hasTwo = currentPlayer.hasTwoOf(cardName);
        if (!hasTwo) {
            throw new UnsupportedOperationException("needTwo");
        }

        int chosenPlayerIndex = ui.promptPlayer("playerID");
        Player chosenPlayer = game.getPlayer(chosenPlayerIndex);
        if (chosenPlayerIndex == currentPlayer.getId()) {
            throw new InputMismatchException("chosenSelfError");
        }

        int chosenCardIndex = ui.promptPlayer("takeCard");
        Card chosenCard = chosenPlayer.chooseCard(chosenCardIndex);
        chosenPlayer.removeCard(chosenCardIndex);
        currentPlayer.addCard(chosenCard);
        game.setPlayer(chosenPlayer);
        game.setPlayer(currentPlayer);

        int cardIndex = currentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);
        currentPlayer.removeCard(cardIndex);
        cardIndex = currentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }
}