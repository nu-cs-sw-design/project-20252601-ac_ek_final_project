package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

import java.util.InputMismatchException;

public class FeralCatCard extends Card{
    private static final int[] COUNTS = {4, 8, 12};

    @Override
    public String getName() {
        return "Feral Cat";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("feralCatCard");

        Player currentPlayer = game.getCurrentPlayer();
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

        int cardIndex;
        if (hasCatCard) {
            cardIndex = currentPlayer.hasCard(catCard.getName());
        } else {
            cardIndex = currentPlayer.hasCard(this.getName());
        }
        game.removeCurrentPlayerCard(cardIndex);
        int cardIndexSecondCard = currentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndexSecondCard);
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }
}