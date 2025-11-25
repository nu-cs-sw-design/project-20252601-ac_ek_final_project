package domain.model.cards.interaction;

import domain.model.Deck;
import domain.model.GameContext;
import domain.model.Player;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

import java.util.List;

public class GarbageCollectionCard extends Card {
    public GarbageCollectionCard() {
        super(new GarbageCollectionCardEffect());
    }

    @Override
    public String getName() {
        return "Garbage Collection";
    }

    private static class GarbageCollectionCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            context.displayMessage("garbageCollectionCard");

            Player currentPlayer = context.getCurrentPlayer();
            int cardIndex = currentPlayer.hasCard("Garbage Collection");
            context.removeCurrentPlayerCard(cardIndex);

            Deck deck = context.getDeck();
            List<Player> players = context.getPlayers();

            for (Player player : players) {
                context.displayFormattedMessage("player", player.getId());
                int index = context.promptPlayer("discard");
                Card card = player.chooseCard(index);
                player.removeCard(index);
                deck.addCard(card);
                context.setPlayer(player);
            }

            deck.shuffle();
            context.setDeck(deck);
        }
    }
}