package domain.model;

import java.util.HashSet;
import java.util.Set;

public class GameConfiguration {
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS_BASE = 5;
    private static final int MAX_PLAYERS_PARTY = 10;
    
    private final int playerCount;
    private final Set<ExpansionPack> expansionPacks;

    public GameConfiguration(int playerCount, Set<ExpansionPack> expansionPacks) {
        this.expansionPacks = new HashSet<>(expansionPacks);
        validateConfiguration(playerCount, this.expansionPacks);
        this.playerCount = playerCount;
    }

    private void validateConfiguration(int playerCount, Set<ExpansionPack> expansionPacks) {
        validatePlayerCount(playerCount, expansionPacks);
    }

    private void validatePlayerCount(int playerCount, Set<ExpansionPack> expansionPacks) {
        if (playerCount < MIN_PLAYERS) {
            throw new IllegalArgumentException("tooFewPlayers");
        }

        int maxPlayers = expansionPacks.contains(ExpansionPack.PARTY_PACK) ? MAX_PLAYERS_PARTY : MAX_PLAYERS_BASE;

        if (playerCount > maxPlayers) {
            if (expansionPacks.contains(ExpansionPack.PARTY_PACK)) {
                throw new IllegalArgumentException("tooManyPlayersWithParty");
            } else {
                throw new IllegalArgumentException("tooManyPlayersWithoutParty");
            }
        }
    }

    public static boolean isValidExpansionNumber(int expansionNumber) {
        return expansionNumber >= 1 && expansionNumber <= 3;
    }

    public static ExpansionPack getExpansionPack(int expansionNumber) {
        switch (expansionNumber) {
            case 1:
                return ExpansionPack.PARTY_PACK;
            case 2:
                return ExpansionPack.STREAKING_KITTENS;
            case 3:
                return ExpansionPack.IMPLODING_KITTENS;
            default:
                throw new IllegalArgumentException("invalidExpansionSelection");
        }
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public Set<ExpansionPack> getExpansionPacks() {
        return new HashSet<>(expansionPacks);
    }

    public int getMaxPlayers() {
        return expansionPacks.contains(ExpansionPack.PARTY_PACK) ? MAX_PLAYERS_PARTY : MAX_PLAYERS_BASE;
    }

    public int getMinPlayers() {
        return MIN_PLAYERS;
    }
}
