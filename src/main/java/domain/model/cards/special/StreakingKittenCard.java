package domain.model.cards.special;

import domain.model.GameContext;
import domain.model.cards.Card;
import domain.model.cards.CardEffect;

public class StreakingKittenCard extends Card{
    public StreakingKittenCard() {
        super(new StreakingKittenCardEffect());
    }

    @Override
    public String getName() {
        return "Streaking Kitten";
    }

    private static class StreakingKittenCardEffect implements CardEffect {
        @Override
        public void execute(GameContext context) {
            throw new UnsupportedOperationException("cantPlayCard");
        }
    }
}