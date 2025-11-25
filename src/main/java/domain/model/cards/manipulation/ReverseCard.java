package domain.model.cards.manipulation;

import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseCard extends Card{
    public ReverseCard() {
        super(new ReverseCardEffect());
    }

    @Override
    public String getName() {
        return "Reverse";
    }

    private static class ReverseCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("reverseCard");

            List<Player> players = new ArrayList<>(context.getPlayers());
            if (players.isEmpty()) {
                throw new IllegalStateException("emptyPlayers");
            }

            Player currentPlayer = context.getCurrentPlayer();
            int cardIndex = currentPlayer.hasCard("Reverse");
            context.removeCurrentPlayerCard(cardIndex);

            if (players.size() == 2) {
                int turns = currentPlayer.getNumberOfTurns();
                if (turns > 0) {
                    context.setCurrentPlayerTurns(turns - 1);
                }
            }
            else {
                Collections.reverse(players);
                context.setPlayers(players);
            }
        }
    }
}