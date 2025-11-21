package domain.Cards;

import domain.Game;
import ui.UI;

public class StreakingKittenCard extends Card{
    @Override
    public String getName() {
        return "Streaking Kitten";
    }

    @Override
    public void playCard(Game game, UI ui) {
        throw new UnsupportedOperationException("cantPlayCard");
    }
}