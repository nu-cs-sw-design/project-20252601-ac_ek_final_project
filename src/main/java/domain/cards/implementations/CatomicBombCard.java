package domain.cards.implementations;

import domain.cards.Card;
import domain.cards.CardManager;
import domain.deck.Deck;
import domain.deck.DeckManager;
import domain.game.GameContext;

public class CatomicBombCard extends Card {
    private static final int END_TURN = 0;

    public CatomicBombCard() {
        super();
    }

    @Override
    public String getName() {
        return "Catomic Bomb";
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("catomicBombCard");

        Deck deck = context.getDeck();
        DeckManager dm = context.getDeckManager();
        dm.moveExplodingKittensToTop(deck);
        context.setDeck(deck);
        context.setCurrentPlayerTurns(END_TURN);
        CardManager cm = context.getCardManager();
        cm.removeCardFromCurrentPlayer(context, "Catomic Bomb");
    }
}