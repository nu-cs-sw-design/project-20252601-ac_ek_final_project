package domain.model.cards;

import domain.model.Game;
import ui.UI;

public abstract class Card {
    public abstract String getName();
    public abstract void playCard(Game game, UI ui);
}