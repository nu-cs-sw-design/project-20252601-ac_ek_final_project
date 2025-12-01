package domain.game;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GameConfigurationTests {

    @Test
    public void testConstructor_twoHumanPlayers_noAI_noExpansions() {
        GameConfiguration config = new GameConfiguration(2, 0, new HashSet<>());
        assertEquals(2, config.getPlayerCount());
        assertEquals(0, config.getAIPlayerCount());
        assertTrue(config.getExpansionIds().isEmpty());
    }

    @Test
    public void testConstructor_threeHumanPlayers_oneAI_noExpansions() {
        GameConfiguration config = new GameConfiguration(2, 1, new HashSet<>());
        assertEquals(3, config.getPlayerCount());
        assertEquals(1, config.getAIPlayerCount());
    }

    @Test
    public void testConstructor_fiveHumanPlayers_noExpansions() {
        GameConfiguration config = new GameConfiguration(5, 0, new HashSet<>());
        assertEquals(5, config.getPlayerCount());
    }

    @Test
    public void testConstructor_allAIPlayers() {
        GameConfiguration config = new GameConfiguration(0, 2, new HashSet<>());
        assertEquals(2, config.getPlayerCount());
        assertEquals(2, config.getAIPlayerCount());
    }

    @Test
    public void testConstructor_withExpansion() {
        Set<String> expansions = new HashSet<>();
        expansions.add("imploding");
        GameConfiguration config = new GameConfiguration(2, 0, expansions);
        assertTrue(config.getExpansionIds().contains("imploding"));
    }

    @Test
    public void testConstructor_tooFewPlayers_throwsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> new GameConfiguration(1, 0, new HashSet<>()));
    }

    @Test
    public void testConstructor_zeroPlayers_throwsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> new GameConfiguration(0, 0, new HashSet<>()));
    }

    @Test
    public void testConstructor_negativeHumanPlayers_throwsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> new GameConfiguration(-1, 2, new HashSet<>()));
    }

    @Test
    public void testConstructor_tooManyPlayers_noPartyExpansion_throwsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> new GameConfiguration(6, 0, new HashSet<>()));
    }

    @Test
    public void testConstructor_negativeAIPlayers_throwsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> new GameConfiguration(2, -1, new HashSet<>()));
    }

    @Test
    public void testGetPlayerCount_returnsHumanPlusAI() {
        GameConfiguration config = new GameConfiguration(3, 2, new HashSet<>());
        assertEquals(5, config.getPlayerCount());
    }

    @Test
    public void testGetPlayerCount_onlyHumans() {
        GameConfiguration config = new GameConfiguration(4, 0, new HashSet<>());
        assertEquals(4, config.getPlayerCount());
    }

    @Test
    public void testGetPlayerCount_onlyAI() {
        GameConfiguration config = new GameConfiguration(0, 3, new HashSet<>());
        assertEquals(3, config.getPlayerCount());
    }

    @Test
    public void testGetAIPlayerCount_returnsAICount() {
        GameConfiguration config = new GameConfiguration(2, 3, new HashSet<>());
        assertEquals(3, config.getAIPlayerCount());
    }

    @Test
    public void testGetAIPlayerCount_noAI() {
        GameConfiguration config = new GameConfiguration(2, 0, new HashSet<>());
        assertEquals(0, config.getAIPlayerCount());
    }

    @Test
    public void testGetExpansionIds_returnsDefensiveCopy() {
        Set<String> expansions = new HashSet<>();
        expansions.add("imploding");
        GameConfiguration config = new GameConfiguration(2, 0, expansions);
        
        Set<String> returnedExpansions = config.getExpansionIds();
        returnedExpansions.add("streaking");
        
        assertEquals(1, config.getExpansionIds().size());
    }

    @Test
    public void testGetExpansionIds_emptySet() {
        GameConfiguration config = new GameConfiguration(2, 0, new HashSet<>());
        assertTrue(config.getExpansionIds().isEmpty());
    }

    @Test
    public void testGetExpansionIds_multipleExpansions() {
        Set<String> expansions = new HashSet<>();
        expansions.add("imploding");
        expansions.add("streaking");
        GameConfiguration config = new GameConfiguration(2, 0, expansions);
        
        assertEquals(2, config.getExpansionIds().size());
        assertTrue(config.getExpansionIds().contains("imploding"));
        assertTrue(config.getExpansionIds().contains("streaking"));
    }

    @Test
    public void testIsValidExpansionNumber_validNumber() {
        assertTrue(GameConfiguration.isValidExpansionNumber(1));
    }

    @Test
    public void testIsValidExpansionNumber_invalidNumber_zero() {
        assertFalse(GameConfiguration.isValidExpansionNumber(0));
    }

    @Test
    public void testIsValidExpansionNumber_invalidNumber_negative() {
        assertFalse(GameConfiguration.isValidExpansionNumber(-1));
    }

    @Test
    public void testIsValidExpansionNumber_invalidNumber_tooLarge() {
        assertFalse(GameConfiguration.isValidExpansionNumber(100));
    }

    @Test
    public void testGetExpansionId_validNumber() {
        String id = GameConfiguration.getExpansionId(1);
        assertNotNull(id);
        assertFalse(id.isEmpty());
    }

    @Test
    public void testGetExpansionId_invalidNumber_throwsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> GameConfiguration.getExpansionId(100));
    }

    @Test
    public void testGetExpansionDisplayName_validNumber() {
        String name = GameConfiguration.getExpansionDisplayName(1);
        assertNotNull(name);
        assertFalse(name.isEmpty());
        assertNotEquals("Unknown", name);
    }

    @Test
    public void testGetExpansionDisplayName_invalidNumber_returnsUnknown() {
        String name = GameConfiguration.getExpansionDisplayName(100);
        assertEquals("Unknown", name);
    }

    @Test
    public void testConstructor_minimumPlayers() {
        GameConfiguration config = new GameConfiguration(2, 0, new HashSet<>());
        assertEquals(2, config.getPlayerCount());
    }

    @Test
    public void testConstructor_maximumPlayersWithoutExpansion() {
        GameConfiguration config = new GameConfiguration(5, 0, new HashSet<>());
        assertEquals(5, config.getPlayerCount());
    }

    @Test
    public void testConstructor_withPartyExpansion_morePlayers() {
        Set<String> expansions = new HashSet<>();
        expansions.add("party_pack");
        GameConfiguration config = new GameConfiguration(8, 0, expansions);
        assertEquals(8, config.getPlayerCount());
    }

    @Test
    public void testConstructor_withPartyExpansion_tooManyPlayers_throwsException() {
        Set<String> expansions = new HashSet<>();
        expansions.add("party_pack");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> new GameConfiguration(11, 0, expansions));
        assertEquals("tooManyPlayersWithParty", exception.getMessage());
    }

    @Test
    public void testValidateAIPlayerCount_negativeAI_throwsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> new GameConfiguration(3, -1, new HashSet<>()));
        assertEquals("negativeAIPlayers", exception.getMessage());
    }

    @Test
    public void testValidateAIPlayerCount_zeroAI_passes() {
        GameConfiguration config = new GameConfiguration(2, 0, new HashSet<>());
        assertEquals(0, config.getAIPlayerCount());
    }

    @Test
    public void testValidateAIPlayerCount_positiveAI_passes() {
        GameConfiguration config = new GameConfiguration(2, 2, new HashSet<>());
        assertEquals(2, config.getAIPlayerCount());
    }
}