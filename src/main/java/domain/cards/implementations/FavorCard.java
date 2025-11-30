package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.game.GameContext;
import domain.player.Player;
import domain.player.PlayerManager;

import java.util.Objects;

public class FavorCard extends Card {
    private static final int[] COUNTS = {4, 6, 10};

    public FavorCard() {
        super();
    }

    @Override
    public String getName() {
        return "Favor";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("favorCard");

        Player currentPlayer = context.getCurrentPlayer();
        PlayerManager ps = context.getPlayerManager();
        CardManager cm = context.getCardManager();
        Player chosenPlayer = cm.chooseValidTargetPlayer(context, "targetPlayerId");

        context.setCurrentPlayer(chosenPlayer);
        context.displayFormattedMessage("player", chosenPlayer.getId());
        int chosenCardIndex = context.promptPlayer("chosenPlayerCardIndex");

        context.setCurrentPlayer(currentPlayer);
        Card chosenCard = ps.chooseCard(chosenPlayer, chosenCardIndex);
        ps.removeCard(chosenPlayer, chosenCardIndex);
        context.setPlayer(chosenPlayer);

        if (!Objects.equals(chosenCard.getName(), "Exploding Kitten")) {
            ps.addCard(currentPlayer, chosenCard);
            context.setPlayer(currentPlayer);
            context.displayMessage("addCard");
        } else {
            chosenCard.executeEffect(context);
        }

        cm.removeCardFromCurrentPlayer(context, "Favor");
    }
}