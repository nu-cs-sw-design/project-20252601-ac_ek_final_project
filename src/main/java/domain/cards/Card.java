package domain.cards;

import domain.game.GameContext;
import domain.game.GameEngine;

public abstract class Card {

    public abstract void executeEffect(GameContext context);
    public abstract String getName();

    public void playCard(GameEngine game) {
        GameContext context = new GameContext(game);
        executeEffect(context);
    }
}