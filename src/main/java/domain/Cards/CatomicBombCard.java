package domain.Cards;

import domain.Deck;
import domain.Game;
import domain.Player;
import ui.UI;

public class CatomicBombCard extends Card{
    private static final int SET_NUMBER_OF_TURNS = 0;

    @Override
    public String getName() {
        return "Catomic Bomb";
    }

    @Override
    public void playCard(Game game, UI ui) {
        ui.displayMessage("catomicBombCard");

        Deck deck = game.getDeck();
        if (deck.isEmpty()){
            throw new UnsupportedOperationException("deckEmpty");
        }

        deck.moveExplodingKittensToTop();
        game.setDeck(deck);

        Player currentPlayer = game.getCurrentPlayer();
        int cardIndex = currentPlayer.hasCard(this.getName());
        game.removeCurrentPlayerCard(cardIndex);

        currentPlayer.setNumberOfTurns(SET_NUMBER_OF_TURNS);
        game.setPlayer(currentPlayer);
    }
}