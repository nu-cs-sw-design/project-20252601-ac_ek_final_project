package domain.cards.expansions;

import domain.cards.Card;
import domain.cards.implementations.*;
import domain.deck.Deck;

public class BaseGameExpansion implements ExpansionStrategy {    
    private static final int BASE_CAT_CARD_COUNT = 4;
    private static final int BASE_NOPE_CARD_COUNT = 5;
    private static final int BASE_ATTACK_CARD_COUNT = 4;
    private static final int BASE_SHUFFLE_CARD_COUNT = 4;
    private static final int BASE_FAVOR_CARD_COUNT = 4;
    private static final int BASE_SKIP_CARD_COUNT = 4;
    private static final int BASE_SEE_FUTURE_CARD_COUNT = 5;
    private static final int BASE_DEFUSE_COUNT = 6;

    @Override
    public String getId() {
        return "base";
    }

    @Override
    public String getDisplayName() {
        return "Base Game";
    }

    @Override
    public int getSelectionNumber() {
        return 0;
    }

    @Override
    public void addCardsToDeck(Deck deck, int playerCount) {
        for (CatCard.CatCardType catCardType : CatCard.CatCardType.values()) {
            addCards(deck, () -> new CatCard(catCardType), BASE_CAT_CARD_COUNT);
        }

        addCards(deck, NopeCard::new, BASE_NOPE_CARD_COUNT);
        addCards(deck, AttackCard::new, BASE_ATTACK_CARD_COUNT);
        addCards(deck, ShuffleCard::new, BASE_SHUFFLE_CARD_COUNT);
        addCards(deck, FavorCard::new, BASE_FAVOR_CARD_COUNT);
        addCards(deck, SkipCard::new, BASE_SKIP_CARD_COUNT);
        addCards(deck, () -> new SeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE), BASE_SEE_FUTURE_CARD_COUNT);
    }

    @Override
    public void addPostDealCards(Deck deck, int playerCount) {
        int explodingKittenCount = playerCount - 1;
        addCards(deck, ExplodingKittenCard::new, explodingKittenCount);
        
        int remainingDefuse = BASE_DEFUSE_COUNT - playerCount;
        if (remainingDefuse > 0) {
            addCards(deck, DefuseCard::new, remainingDefuse);
        }
    }

    @Override
    public int getMaxPlayers() {
        return 5;
    }

    protected void addCards(Deck deck, java.util.function.Supplier<Card> cardFactory, int count) {
        for (int i = 0; i < count; i++) {
            deck.addCard(cardFactory.get());
        }
    }
}