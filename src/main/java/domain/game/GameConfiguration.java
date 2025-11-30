package domain.game;

import domain.cards.ExpansionPack;
import domain.player.Hand;
import domain.player.Player;
import domain.player.PlayerController;

import java.util.HashSet;
import java.util.Set;

public class GameConfiguration {
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS_BASE = 5;
    private static final int MAX_PLAYERS_PARTY = 10;
    private static final int EXPANSION_PARTY_PACK = 1;
    private static final int EXPANSION_STREAKING_KITTENS = 2;
    private static final int EXPANSION_IMPLODING_KITTENS = 3;
    private static final int MIN_EXPANSION_NUMBER = 1;
    private static final int MAX_EXPANSION_NUMBER = 3;
    
    private final int playerCount;
    private final int aiPlayerCount;
    private final Set<ExpansionPack> expansionPacks;

    public GameConfiguration(int numHumanPlayers, int numAIPlayers, Set<ExpansionPack> expansionPacks) {
        this.expansionPacks = new HashSet<>(expansionPacks);
        this.playerCount = numHumanPlayers + numAIPlayers;
        this.aiPlayerCount = numAIPlayers;
        validateConfiguration(this.playerCount, numAIPlayers, this.expansionPacks);
    }

    private void validateConfiguration(int playerCount, int aiPlayerCount, Set<ExpansionPack> expansionPacks) {
        validatePlayerCount(playerCount, expansionPacks);
        validateAIPlayerCount(playerCount, aiPlayerCount);
    }

    private void validateAIPlayerCount(int playerCount, int aiPlayerCount) {
        if (aiPlayerCount < 0) {
            throw new IllegalArgumentException("negativeAIPlayers");
        }
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
        return expansionNumber >= MIN_EXPANSION_NUMBER && expansionNumber <= MAX_EXPANSION_NUMBER;
    }

    public static ExpansionPack getExpansionPack(int expansionNumber) {
        switch (expansionNumber) {
            case EXPANSION_PARTY_PACK:
                return ExpansionPack.PARTY_PACK;
            case EXPANSION_STREAKING_KITTENS:
                return ExpansionPack.STREAKING_KITTENS;
            case EXPANSION_IMPLODING_KITTENS:
                return ExpansionPack.IMPLODING_KITTENS;
            default:
                throw new IllegalArgumentException("invalidExpansionSelection");
        }
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public int getAIPlayerCount() {
        return aiPlayerCount;
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