package domain.Cards;

import domain.Game;
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