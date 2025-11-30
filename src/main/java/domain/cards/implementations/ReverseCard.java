package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseCard extends Card {
    private static final int[] COUNTS = {4, 6, 8};
    private static final int END_TURN = 0;

    public ReverseCard() {
        super();
    }

    @Override
    public String getName() {
        return "Reverse";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("reverseCard");

        context.setCurrentPlayerTurns(END_TURN);
        List<Player> players = new ArrayList<>(context.getPlayers());
        Player current = players.remove(0);
        Collections.reverse(players);
        players.add(0, current);
        context.setPlayers(players);
        CardManager cm = context.getCardManager();
        cm.removeCardFromCurrentPlayer(context, "Reverse");
    }
}