package domain.model.cards.interaction;

import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

import java.util.InputMismatchException;

public class CurseOfTheCatButtCard extends Card{
    public CurseOfTheCatButtCard() {
        super(new CurseOfTheCatButtCardEffect());
    }

    @Override
    public String getName() {
        return "Curse of the Cat Butt";
    }

    private static class CurseOfTheCatButtCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("curseCatButt");

            Player currentPlayer = context.getCurrentPlayer();
            int chosenPlayerIndex = context.promptPlayer("playerIDCurse");
            if (chosenPlayerIndex <= 0 || chosenPlayerIndex > context.getPlayers().size()) {
                throw new UnsupportedOperationException("invalidPlayerID");
            }
            if (currentPlayer.getId() == chosenPlayerIndex) {
                throw new InputMismatchException("chosenSelfError");
            }

            Player chosenPlayer = context.getPlayer(chosenPlayerIndex);
            chosenPlayer.setHandVisibility(false);
            chosenPlayer.shuffleHand();
            context.setPlayer(chosenPlayer);

            int cardIndex = currentPlayer.hasCard("Curse of the Cat Butt");
            context.removeCurrentPlayerCard(cardIndex);
        }
    }
}