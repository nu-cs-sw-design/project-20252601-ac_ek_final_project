package domain.model.cards;

import domain.model.GameContext;

public interface CardEffect {
    void execute(GameContext context);
}
