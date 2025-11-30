package domain.cards.expansions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class ExpansionRegistry {
    private static final Map<String, ExpansionStrategy> expansionsById = new HashMap<>();
    private static final Map<Integer, ExpansionStrategy> expansionsByNumber = new HashMap<>();
    
    static {
        register(new PartyPackExpansion());
        register(new StreakingKittensExpansion());
        register(new ImplodingKittensExpansion());
    }
    
    private ExpansionRegistry() {
    }
    
    public static void register(ExpansionStrategy expansion) {
        expansionsById.put(expansion.getId(), expansion);
        expansionsByNumber.put(expansion.getSelectionNumber(), expansion);
    }
    
    public static Optional<ExpansionStrategy> getById(String id) {
        return Optional.ofNullable(expansionsById.get(id));
    }
    
    public static Optional<ExpansionStrategy> getByNumber(int number) {
        return Optional.ofNullable(expansionsByNumber.get(number));
    }
    
    public static boolean isValidNumber(int number) {
        return expansionsByNumber.containsKey(number);
    }
    
    public static Collection<ExpansionStrategy> getAllExpansions() {
        return expansionsById.values();
    }
    
    public static int getExpansionCount() {
        return expansionsById.size();
    }
    
    public static int getMaxSelectionNumber() {
        return expansionsByNumber.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
    }
}