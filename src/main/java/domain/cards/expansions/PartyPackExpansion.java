package domain.cards.expansions;

import domain.cards.Card;
import domain.cards.implementations.*;
import domain.deck.Deck;

public class PartyPackExpansion implements ExpansionStrategy {    
    private static final int SMALL_GAME_MAX = 3;
    private static final int MEDIUM_GAME_MAX = 7;
    
    private static final int[] CAT_CARD_COUNTS = {4, 6, 8};
    private static final int[] NOPE_CARD_COUNTS = {5, 7, 10};
    private static final int[] ATTACK_CARD_COUNTS = {2, 3, 5};
    private static final int[] SHUFFLE_CARD_COUNTS = {4, 6, 8};
    private static final int[] FAVOR_CARD_COUNTS = {4, 6, 10};
    private static final int[] SKIP_CARD_COUNTS = {4, 6, 10};
    private static final int[] SEE_FUTURE_COUNTS = {4, 6, 10};
    
    private static final int BASE_DEFUSE_COUNT = 6;
    private static final int PARTY_DEFUSE_OFFSET = 2;

    @Override
    public String getId() {
        return "party_pack";
    }

    @Override
    public String getDisplayName() {
        return "Party Pack";
    }

    @Override
    public int getSelectionNumber() {
        return 1;
    }

    @Override
    public void addCardsToDeck(Deck deck, int playerCount) {
        int countIndex = computeCountIndex(playerCount);

        for (CatCard.CatCardType catCardType : CatCard.CatCardType.values()) {
            addCards(deck, () -> new CatCard(catCardType), CAT_CARD_COUNTS[countIndex]);
        }

        addCards(deck, NopeCard::new, NOPE_CARD_COUNTS[countIndex]);
        addCards(deck, AttackCard::new, ATTACK_CARD_COUNTS[countIndex]);
        addCards(deck, ShuffleCard::new, SHUFFLE_CARD_COUNTS[countIndex]);
        addCards(deck, FavorCard::new, FAVOR_CARD_COUNTS[countIndex]);
        addCards(deck, SkipCard::new, SKIP_CARD_COUNTS[countIndex]);
        addCards(deck, () -> new SeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE), SEE_FUTURE_COUNTS[countIndex]);
    }

    @Override
    public void addPostDealCards(Deck deck, int playerCount) {
        int explodingKittenCount = playerCount - 1;
        addCards(deck, ExplodingKittenCard::new, explodingKittenCount);
        
        int totalDefuse = Math.max(BASE_DEFUSE_COUNT, playerCount + PARTY_DEFUSE_OFFSET);
        int remainingDefuse = totalDefuse - playerCount;
        if (remainingDefuse > 0) {
            addCards(deck, DefuseCard::new, remainingDefuse);
        }
    }

    @Override
    public int getMaxPlayers() {
        return 10;
    }

    @Override
    public boolean isBaseGameModifier() {
        return true;
    }

    @Override
    public int getAdditionalDefuseCards(int playerCount) {
        return PARTY_DEFUSE_OFFSET;
    }

    private int computeCountIndex(int playerCount) {
        if (playerCount <= SMALL_GAME_MAX) {
            return 0;
        } else if (playerCount <= MEDIUM_GAME_MAX) {
            return 1;
        } else {
            return 2;
        }
    }

    private void addCards(Deck deck, java.util.function.Supplier<Card> cardFactory, int count) {
        for (int i = 0; i < count; i++) {
            deck.addCard(cardFactory.get());
        }
    }
}