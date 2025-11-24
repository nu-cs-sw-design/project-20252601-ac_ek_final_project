package domain.model.cards;

import domain.model.Game;
import domain.model.GameContext;
import ui.UI;

public abstract class Card {
    private final CardEffect effect;

    protected Card(CardEffect effect) {
        this.effect = effect;
    }

    public CardEffect getEffect() {
        return effect;
    }

    public abstract String getName();

    public void playCard(Game game, UI ui) {
        GameContext context = new GameContext(game, ui);
        getEffect().execute(context);
    }
}