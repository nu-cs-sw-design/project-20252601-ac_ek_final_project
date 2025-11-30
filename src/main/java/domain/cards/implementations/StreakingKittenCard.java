package domain.cards.implementations;

import domain.cards.Card;
import domain.game.GameContext;

public class StreakingKittenCard extends Card {

    public StreakingKittenCard() {
        super();
    }

    @Override
    public String getName() {
        return "Streaking Kitten";
    }

    @Override
    public void executeEffect(GameContext context) {
        context.displayMessage("streakingKittenCard");
    }
}