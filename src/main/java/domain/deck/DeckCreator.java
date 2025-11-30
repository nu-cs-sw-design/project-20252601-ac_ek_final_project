package domain.deck;

import domain.cards.Card;
import domain.cards.expansions.BaseGameExpansion;
import domain.cards.expansions.ExpansionRegistry;
import domain.cards.expansions.ExpansionStrategy;
import domain.cards.implementations.DefuseCard;
import domain.cards.implementations.ExplodingKittenCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DeckCreator {
    private static final int MIN_PLAYERS = 2;
    private static final int BASE_DEFUSE_COUNT = 6;

    private final ExpansionStrategy baseGame;

    public DeckCreator() {
        this.baseGame = new BaseGameExpansion();
    }

    public Deck createDeck(int playerCount, Set<String> expansionIds) {
        List<ExpansionStrategy> expansions = resolveExpansions(expansionIds);
        validatePlayerCount(playerCount, expansions);
        
        Deck deck = new Deck();
        
        ExpansionStrategy baseModifier = findBaseGameModifier(expansions);
        
        if (baseModifier != null) {
            baseModifier.addCardsToDeck(deck, playerCount);
        } else {
            baseGame.addCardsToDeck(deck, playerCount);
        }
        
        for (ExpansionStrategy expansion : expansions) {
            if (!expansion.isBaseGameModifier()) {
                expansion.addCardsToDeck(deck, playerCount);
            }
        }
        
        return deck;
    }

    public void addRemainingCards(Deck deck, int numberOfPlayers, Set<String> expansionIds) {
        List<ExpansionStrategy> expansions = resolveExpansions(expansionIds);
        
        int totalDefuseCards = calculateTotalDefuseCards(numberOfPlayers, expansions);
        int remainingDefuseCards = totalDefuseCards - numberOfPlayers;
        addCards(deck, DefuseCard::new, remainingDefuseCards);
        
        int explodingKittenCount = calculateExplodingKittenCount(numberOfPlayers, expansions);
        addCards(deck, ExplodingKittenCard::new, explodingKittenCount);
        
        for (ExpansionStrategy expansion : expansions) {
            expansion.addPostDealCards(deck, numberOfPlayers);
        }
        
        deck.shuffle();
    }

    private List<ExpansionStrategy> resolveExpansions(Set<String> expansionIds) {
        List<ExpansionStrategy> expansions = new ArrayList<>();
        for (String id : expansionIds) {
            ExpansionRegistry.getById(id).ifPresent(expansions::add);
        }
        return expansions;
    }

    private ExpansionStrategy findBaseGameModifier(List<ExpansionStrategy> expansions) {
        return expansions.stream().filter(ExpansionStrategy::isBaseGameModifier).findFirst().orElse(null);
    }

    private void validatePlayerCount(int playerCount, List<ExpansionStrategy> expansions) {
        int maxPlayers = expansions.stream().mapToInt(ExpansionStrategy::getMaxPlayers).max().orElse(baseGame.getMaxPlayers());
        
        if (playerCount < MIN_PLAYERS || playerCount > maxPlayers) {
            throw new IllegalArgumentException("Invalid player count for selected expansions");
        }
    }

    private int calculateTotalDefuseCards(int playerCount, List<ExpansionStrategy> expansions) {
        int additionalDefuse = expansions.stream().mapToInt(e -> e.getAdditionalDefuseCards(playerCount)).sum();
        return Math.max(BASE_DEFUSE_COUNT, playerCount + additionalDefuse);
    }

    private int calculateExplodingKittenCount(int playerCount, List<ExpansionStrategy> expansions) {
        int baseCount = playerCount - 1;
        int additional = expansions.stream().mapToInt(ExpansionStrategy::getAdditionalExplodingKittens).sum();
        return baseCount + additional;
    }

    private void addCards(Deck deck, java.util.function.Supplier<Card> cardFactory, int count) {
        for (int i = 0; i < count; i++) {
            deck.addCard(cardFactory.get());
        }
    }
}