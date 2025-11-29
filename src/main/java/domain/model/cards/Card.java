package domain.model.cards;

import domain.model.Game;
import domain.model.GameContext;

public abstract class Card {
    private final CardEffect effect;

    protected Card(CardEffect effect) {
        this.effect = effect;
    }

    public CardEffect getEffect() {
        return effect;
    }

    public abstract String getName();

    public void playCard(Game game) {
        GameContext context = new GameContext(game);
        getEffect().execute(context);
    }
}