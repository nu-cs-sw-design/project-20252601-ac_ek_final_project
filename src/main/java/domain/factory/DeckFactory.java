package domain.factory;

import domain.model.Deck;
import domain.model.ExpansionPack;
import domain.model.cards.*;
import domain.model.cards.attack.*;
import domain.model.cards.draw.*;
import domain.model.cards.interaction.*;
import domain.model.cards.manipulation.*;
import domain.model.cards.special.*;

import java.util.Set;

public class DeckFactory {
    private final CardFactory cardFactory;

    public DeckFactory() {
        this.cardFactory = new CardFactory();
    }

    public DeckFactory(CardFactory cardFactory) {
        this.cardFactory = cardFactory;
    }

    public Deck createDeck(int playerCount, Set<ExpansionPack> expansionPacks) {
        Deck deck = new Deck();
        
        boolean hasPartyPack = expansionPacks.contains(ExpansionPack.PARTY_PACK);
        boolean hasStreakingKittens = expansionPacks.contains(ExpansionPack.STREAKING_KITTENS);
        boolean hasImplodingKittens = expansionPacks.contains(ExpansionPack.IMPLODING_KITTENS);
        
        int maxPlayers = hasPartyPack ? 10 : 5;
        if (playerCount < 2 || playerCount > maxPlayers) {
            throw new IllegalArgumentException("Invalid player count for selected expansions");
        }
        
        if (hasPartyPack) {
            createPartyPackDeck(deck, playerCount);
        } else {
            createBaseGameDeck(deck, playerCount);
        }
        
        if (hasStreakingKittens) {
            addStreakingKittensExpansion(deck);
        }
        
        if (hasImplodingKittens) {
            addImplodingKittensExpansion(deck);
        }
        
        return deck;
    }

    private void createBaseGameDeck(Deck deck, int playerCount) {
        for (CatCard.CatCardType catCardType : CatCard.CatCardType.values()) {
            addCards(deck, cardFactory.createCatCard(catCardType), 4);
        }

        addCards(deck, cardFactory.createNopeCard(), 5);
        addCards(deck, cardFactory.createAttackCard(), 4);
        addCards(deck, cardFactory.createShuffleCard(), 4);
        addCards(deck, cardFactory.createFavorCard(), 4);
        addCards(deck, cardFactory.createSkipCard(), 4);
        addCards(deck, cardFactory.createSeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE), 5);        
    }

    private void createPartyPackDeck(Deck deck, int playerCount) {
        int countIndex = computeCountIndex(playerCount);

        for (CatCard.CatCardType catCardType : CatCard.CatCardType.values()) {
            addCards(deck, cardFactory.createCatCard(catCardType), CatCard.getCounts()[countIndex]);
        }

        addCards(deck, cardFactory.createNopeCard(), NopeCard.getCounts()[countIndex]);
        addCards(deck, cardFactory.createAttackCard(), AttackCard.getCounts()[countIndex]);
        addCards(deck, cardFactory.createShuffleCard(), ShuffleCard.getCounts()[countIndex]);
        addCards(deck, cardFactory.createFavorCard(), FavorCard.getCounts()[countIndex]);
        addCards(deck, cardFactory.createSkipCard(), SkipCard.getCounts()[countIndex]);
        addCards(deck, cardFactory.createSeeTheFutureCard(SeeTheFutureCard.PeekOption.THREE), 
                SeeTheFutureCard.getCounts()[countIndex]);
    }

    private void addStreakingKittensExpansion(Deck deck) {
        addCards(deck, cardFactory.createStreakingKittenCard(), 1);
        addCards(deck, cardFactory.createSuperSkipCard(), 1);
        addCards(deck, cardFactory.createSeeTheFutureCard(SeeTheFutureCard.PeekOption.FIVE), 1);
        addCards(deck, cardFactory.createAlterTheFutureCard(AlterTheFutureCard.PeekOption.FIVE), 1);
        addCards(deck, cardFactory.createSwapTopAndBottomCard(), 3);
        addCards(deck, cardFactory.createGarbageCollectionCard(), 1);
        addCards(deck, cardFactory.createCatomicBombCard(), 1);
        addCards(deck, cardFactory.createMarkCard(), 3);
        addCards(deck, cardFactory.createCurseOfTheCatButtCard(), 2);
    }

    private void addImplodingKittensExpansion(Deck deck) {
        addCards(deck, cardFactory.createReverseCard(), 4);
        addCards(deck, cardFactory.createDrawFromTheBottomCard(), 4);
        addCards(deck, cardFactory.createFeralCatCard(), 4);
        addCards(deck, cardFactory.createAlterTheFutureCard(AlterTheFutureCard.PeekOption.THREE), 3);
        addCards(deck, cardFactory.createTargetedAttackCard(), 2);
    }

    public int getExplodingKittenCount(int playerCount, boolean hasStreakingKittens) {
        int baseCount = playerCount - 1;
        return hasStreakingKittens ? baseCount + 1 : baseCount;
    }

    public int getDefuseCardCount(int playerCount, boolean hasPartyPack) {
        if (hasPartyPack) {
            return Math.max(6, playerCount + 2);
        }
        return 6;
    }

    public void addExplodingKittens(Deck deck, int count) {
        addCards(deck, cardFactory.createExplodingKittenCard(), count);
    }

    public void addImplodingKitten(Deck deck) {
        addCards(deck, cardFactory.createImplodingKittenCard(ImplodingKittenCard.DrawnBefore.NOT_DRAWN), 1);
    }

    public void addRemainingCards(Deck deck, int numberOfPlayers, Set<ExpansionPack> expansionPacks) {
        boolean hasPartyPack = expansionPacks.contains(ExpansionPack.PARTY_PACK);
        boolean hasStreakingKittens = expansionPacks.contains(ExpansionPack.STREAKING_KITTENS);
        boolean hasImplodingKittens = expansionPacks.contains(ExpansionPack.IMPLODING_KITTENS);

        int totalDefuseCards = getDefuseCardCount(numberOfPlayers, hasPartyPack);
        int remainingDefuseCards = totalDefuseCards - numberOfPlayers;
        addCards(deck, cardFactory.createDefuseCard(), remainingDefuseCards);

        int explodingKittenCount = getExplodingKittenCount(numberOfPlayers, hasStreakingKittens);
        addExplodingKittens(deck, explodingKittenCount);

        if (hasImplodingKittens) {
            addImplodingKitten(deck);
        }

        deck.shuffle();
    }

    private void addCards(Deck deck, Card card, int count) {
        for (int i = 0; i < count; i++) {
            deck.addCard(card);
        }
    }

    private int computeCountIndex(int playerCount) {
        if (playerCount <= 3) {
            return 0;
        } else if (playerCount <= 7) {
            return 1;
        } else {
            return 2;
        }
    }
}
