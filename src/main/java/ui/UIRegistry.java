package ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Collection;
import java.util.function.Supplier;

public final class UIRegistry {
    private static final Map<String, Supplier<GameUI>> uiFactories = new HashMap<>();
    private static final Map<String, String> uiDisplayNames = new HashMap<>();
    
    static {
        register("console", "Console (Text)", ConsoleUI::new);
        register("gui", "Graphical (Swing)", SwingGUI::new);
    }
    
    private UIRegistry() {
    }
    
    public static void register(String type, String displayName, Supplier<GameUI> factory) {
        uiFactories.put(type.toLowerCase(), factory);
        uiDisplayNames.put(type.toLowerCase(), displayName);
    }

    public static Optional<GameUI> create(String type) {
        Supplier<GameUI> factory = uiFactories.get(type.toLowerCase());
        if (factory != null) {
            return Optional.of(factory.get());
        }
        return Optional.empty();
    }
    
    public static GameUI createDefault() {
        return new ConsoleUI();
    }
    
    public static boolean isRegistered(String type) {
        return uiFactories.containsKey(type.toLowerCase());
    }
    
    public static Collection<String> getAvailableTypes() {
        return uiFactories.keySet();
    }

}