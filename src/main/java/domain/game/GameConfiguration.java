package domain.game;

import domain.cards.expansions.ExpansionRegistry;
import domain.cards.expansions.ExpansionStrategy;

import java.util.HashSet;
import java.util.Set;

public class GameConfiguration {
    private static final int MIN_PLAYERS = 2;
    private static final int DEFAULT_MAX_PLAYERS = 5;
    
    private final int playerCount;
    private final int aiPlayerCount;
    private final Set<String> expansionIds;

    public GameConfiguration(int numHumanPlayers, int numAIPlayers, Set<String> expansionIds) {
        this.expansionIds = new HashSet<>(expansionIds);
        this.playerCount = numHumanPlayers + numAIPlayers;
        this.aiPlayerCount = numAIPlayers;
        validateConfiguration(this.playerCount, numAIPlayers, this.expansionIds);
    }

    private void validateConfiguration(int playerCount, int aiPlayerCount, Set<String> expansionIds) {
        validatePlayerCount(playerCount, expansionIds);
        validateAIPlayerCount(playerCount, aiPlayerCount);
    }

    private void validateAIPlayerCount(int playerCount, int aiPlayerCount) {
        if (aiPlayerCount < 0) {
            throw new IllegalArgumentException("negativeAIPlayers");
        }
    }

    private void validatePlayerCount(int playerCount, Set<String> expansionIds) {
        if (playerCount < MIN_PLAYERS) {
            throw new IllegalArgumentException("tooFewPlayers");
        }

        int maxPlayers = getMaxPlayersForExpansions(expansionIds);

        if (playerCount > maxPlayers) {
            if (maxPlayers > DEFAULT_MAX_PLAYERS) {
                throw new IllegalArgumentException("tooManyPlayersWithParty");
            } else {
                throw new IllegalArgumentException("tooManyPlayersWithoutParty");
            }
        }
    }

    private int getMaxPlayersForExpansions(Set<String> expansionIds) {
        return expansionIds.stream()
                .map(ExpansionRegistry::getById)
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .mapToInt(ExpansionStrategy::getMaxPlayers)
                .max().orElse(DEFAULT_MAX_PLAYERS);
    }

    public static boolean isValidExpansionNumber(int expansionNumber) {
        return ExpansionRegistry.isValidNumber(expansionNumber);
    }

    public static String getExpansionId(int expansionNumber) {
        return ExpansionRegistry.getByNumber(expansionNumber)
                .map(ExpansionStrategy::getId)
                .orElseThrow(() -> new IllegalArgumentException("invalidExpansionSelection"));
    }

    public static String getExpansionDisplayName(int expansionNumber) {
        return ExpansionRegistry.getByNumber(expansionNumber).map(ExpansionStrategy::getDisplayName).orElse("Unknown");
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public int getAIPlayerCount() {
        return aiPlayerCount;
    }

    public Set<String> getExpansionIds() {
        return new HashSet<>(expansionIds);
    }

}