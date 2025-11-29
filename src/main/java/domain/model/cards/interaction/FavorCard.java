package domain.model.cards.interaction;

import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

import java.util.InputMismatchException;
import java.util.Objects;

public class FavorCard extends Card{
    private static final int[] COUNTS = {2, 4, 6};

    public FavorCard() {
        super(new FavorCardEffect());
    }

    @Override
    public String getName() {
        return "Favor";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    private static class FavorCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("favorCard");

            Player curentPlayer = context.getCurrentPlayer();
            int chosenPlayerID = context.promptPlayer("targetPlayerId");
            if (chosenPlayerID == curentPlayer.getId()) {
                throw new InputMismatchException("chosenSelfError");
            }

            Player chosenPlayer = context.getPlayer(chosenPlayerID);
            
            context.setCurrentPlayer(chosenPlayer);
            context.displayFormattedMessage("player", chosenPlayer.getId());
            int chosenCardIndex = context.promptPlayer("chosenPlayerCardIndex");
            context.setCurrentPlayer(curentPlayer);

            Card choosenCard = chosenPlayer.chooseCard(chosenCardIndex);
            chosenPlayer.removeCard(chosenCardIndex);
            context.setPlayer(chosenPlayer);

            if (!Objects.equals(choosenCard.getName(), "Exploding Kitten")) {
                curentPlayer.addCard(choosenCard);
                context.setPlayer(curentPlayer);
                context.displayMessage("addCard");
            } else {
                choosenCard.getEffect().execute(context);
            }

            int cardIndex = curentPlayer.hasCard("Favor");
            context.removeCurrentPlayerCard(cardIndex);
        }
    }
}