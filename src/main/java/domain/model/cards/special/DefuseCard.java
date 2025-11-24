package domain.model.cards.special;

import domain.model.Game;
import domain.model.cards.Card;
import ui.UI;

public class DefuseCard extends Card{
    @Override
    public String getName() {
        return "Defuse";
    }

    @Override
    public void playCard(Game game, UI ui) {
        throw new UnsupportedOperationException("cantPlayCard");
    }
}