package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.game.GameContext;
import domain.player.Player;
import domain.player.PlayerManager;

import java.util.List;

public class GarbageCollectionCard extends Card {
    private static final int[] COUNTS = {2, 3, 4};

    public GarbageCollectionCard() {
        super();
    }

    @Override
    public String getName() {
        return "Garbage Collection";
    }

    public static int[] getCounts() {
        return COUNTS.clone();
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("garbageCollectionCard");

        CardManager cm = context.getCardManager();
        cm.removeCardFromCurrentPlayer(context, "Garbage Collection");

        Deck deck = context.getDeck();
        PlayerManager ps = context.getPlayerManager();
        List<Player> players = context.getPlayers();
        for (Player player : players) {
            context.displayFormattedMessage("player", player.getId());
            int index = context.promptPlayer("discard");
            Card card = ps.chooseCard(player, index);
            ps.removeCard(player, index);
            deck.addCard(card);
            context.setPlayer(player);
        }
        deck.shuffle();
        context.setDeck(deck);
    }
}