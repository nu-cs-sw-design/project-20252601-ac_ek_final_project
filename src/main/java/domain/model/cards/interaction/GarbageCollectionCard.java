package domain.model.cards.interaction;

import domain.model.Deck;
import domain.model.Game;
import domain.model.Player;
import domain.model.cards.Card;
import ui.UI;

import java.util.List;

public class GarbageCollectionCard extends Card {
    @Override
    public String getName() {
        return "Garbage Collection";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("garbageCollectionCard");

        Player currentPlayer = game.getCurrentPlayer();
        int cardIndex = currentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);

        Deck deck = game.getDeck();
        List<Player> players = game.getPlayers();

        for (Player player : players) {
            ui.displayFormattedMessage("player", player.getId());
            int index = ui.promptPlayer("discard");
            Card card = player.chooseCard(index);
            player.removeCard(index);
            deck.addCard(card);
            game.setPlayer(player);
        }

        deck.shuffle();
        game.setDeck(deck);
    }
}