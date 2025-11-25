package domain.model.cards.interaction;

import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

public class MarkCard extends Card {
    public MarkCard() {
        super(new MarkCardEffect());
    }

    @Override
    public String getName() {
        return "Mark";
    }

    private static class MarkCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("markCard");

            int targetPlayerId = context.promptPlayer("targetPlayerId");
            if (targetPlayerId <= 0 || targetPlayerId > context.getPlayers().size()) {
                throw new UnsupportedOperationException("invalidPlayerID");
            }

            Player targetPlayer = context.getPlayer(targetPlayerId);
            if (targetPlayerId == context.getCurrentPlayer().getId()) {
                throw new UnsupportedOperationException("chosenSelfError");
            }

            int targetCardIndex = context.promptPlayer("targetCardIndex");
            if (targetCardIndex < 0 || targetCardIndex >= targetPlayer.getHandCount()) {
                throw new UnsupportedOperationException("invalidCardIndex");
            }

            Card targetCard = targetPlayer.chooseCard(targetCardIndex);
            if (targetPlayer.getVisibleHand().contains(targetCard)) {
                throw new UnsupportedOperationException("alreadyVisible");
            }

            targetPlayer.addVisibleCard(targetCard);
            context.setPlayer(targetPlayer);

            Player currentPlayer = context.getCurrentPlayer();
            int cardIndex = currentPlayer.hasCard("Mark");
            context.removeCurrentPlayerCard(cardIndex);
        }
    }
}