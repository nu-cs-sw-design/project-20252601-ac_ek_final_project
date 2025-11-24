package domain.model.cards.manipulation;

import domain.model.Deck;
import domain.model.Game;
import domain.model.Player;
import domain.model.cards.Card;
import ui.UI;

public class SwapTopAndBottomCard extends Card{
    @Override
    public String getName() {
        return "Swap Top and Bottom";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("swapTopAndBottomCard");

        Deck deck = game.getDeck();
        if (deck.isEmpty()){
            throw new UnsupportedOperationException("deckEmpty");
        } else if (deck.numberOfCards() > 1) {
            deck.swapTopAndBottom();
            game.setDeck(deck);
        }

        Player currentPlayer = game.getCurrentPlayer();
        int cardIndex = currentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);
    }
}