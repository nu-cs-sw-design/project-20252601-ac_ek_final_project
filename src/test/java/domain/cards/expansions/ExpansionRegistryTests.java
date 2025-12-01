package domain.cards.expansions;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ExpansionRegistryTests {

    @Test
    public void testGetById_partyPack() {
        Optional<ExpansionStrategy> result = ExpansionRegistry.getById("party_pack");
        assertTrue(result.isPresent());
        assertEquals("Party Pack", result.get().getDisplayName());
    }

    @Test
    public void testGetById_streakingKittens() {
        Optional<ExpansionStrategy> result = ExpansionRegistry.getById("streaking_kittens");
        assertTrue(result.isPresent());
        assertEquals("Streaking Kittens", result.get().getDisplayName());
    }

    @Test
    public void testGetById_implodingKittens() {
        Optional<ExpansionStrategy> result = ExpansionRegistry.getById("imploding_kittens");
        assertTrue(result.isPresent());
        assertEquals("Imploding Kittens", result.get().getDisplayName());
    }

    @Test
    public void testGetById_nonExistent() {
        Optional<ExpansionStrategy> result = ExpansionRegistry.getById("non_existent");
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetById_null() {
        Optional<ExpansionStrategy> result = ExpansionRegistry.getById(null);
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetById_emptyString() {
        Optional<ExpansionStrategy> result = ExpansionRegistry.getById("");
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetByNumber_one_partyPack() {
        Optional<ExpansionStrategy> result = ExpansionRegistry.getByNumber(1);
        assertTrue(result.isPresent());
        assertEquals("party_pack", result.get().getId());
    }

    @Test
    public void testGetByNumber_two_streakingKittens() {
        Optional<ExpansionStrategy> result = ExpansionRegistry.getByNumber(2);
        assertTrue(result.isPresent());
        assertEquals("streaking_kittens", result.get().getId());
    }

    @Test
    public void testGetByNumber_three_implodingKittens() {
        Optional<ExpansionStrategy> result = ExpansionRegistry.getByNumber(3);
        assertTrue(result.isPresent());
        assertEquals("imploding_kittens", result.get().getId());
    }

    @Test
    public void testGetByNumber_zero_nonExistent() {
        Optional<ExpansionStrategy> result = ExpansionRegistry.getByNumber(0);
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetByNumber_negative() {
        Optional<ExpansionStrategy> result = ExpansionRegistry.getByNumber(-1);
        assertFalse(result.isPresent());
    }

    @Test
    public void testGetByNumber_highNumber() {
        Optional<ExpansionStrategy> result = ExpansionRegistry.getByNumber(100);
        assertFalse(result.isPresent());
    }

    @Test
    public void testIsValidNumber_one() {
        assertTrue(ExpansionRegistry.isValidNumber(1));
    }

    @Test
    public void testIsValidNumber_two() {
        assertTrue(ExpansionRegistry.isValidNumber(2));
    }

    @Test
    public void testIsValidNumber_three() {
        assertTrue(ExpansionRegistry.isValidNumber(3));
    }

    @Test
    public void testIsValidNumber_zero() {
        assertFalse(ExpansionRegistry.isValidNumber(0));
    }

    @Test
    public void testIsValidNumber_negative() {
        assertFalse(ExpansionRegistry.isValidNumber(-1));
    }

    @Test
    public void testIsValidNumber_four() {
        assertFalse(ExpansionRegistry.isValidNumber(4));
    }

    @Test
    public void testIsValidNumber_highNumber() {
        assertFalse(ExpansionRegistry.isValidNumber(999));
    }

    @Test
    public void testGetByIdAndNumber_consistent_partyPack() {
        Optional<ExpansionStrategy> byId = ExpansionRegistry.getById("party_pack");
        Optional<ExpansionStrategy> byNumber = ExpansionRegistry.getByNumber(1);
        
        assertTrue(byId.isPresent());
        assertTrue(byNumber.isPresent());
        assertEquals(byId.get().getId(), byNumber.get().getId());
    }

    @Test
    public void testGetByIdAndNumber_consistent_streakingKittens() {
        Optional<ExpansionStrategy> byId = ExpansionRegistry.getById("streaking_kittens");
        Optional<ExpansionStrategy> byNumber = ExpansionRegistry.getByNumber(2);
        
        assertTrue(byId.isPresent());
        assertTrue(byNumber.isPresent());
        assertEquals(byId.get().getId(), byNumber.get().getId());
    }

    @Test
    public void testGetByIdAndNumber_consistent_implodingKittens() {
        Optional<ExpansionStrategy> byId = ExpansionRegistry.getById("imploding_kittens");
        Optional<ExpansionStrategy> byNumber = ExpansionRegistry.getByNumber(3);
        
        assertTrue(byId.isPresent());
        assertTrue(byNumber.isPresent());
        assertEquals(byId.get().getId(), byNumber.get().getId());
    }
}