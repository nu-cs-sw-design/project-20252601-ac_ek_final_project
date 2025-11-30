package ui;

import java.util.Set;

public interface GameUI {
    void displayMessage(String key);
    void displayFormattedMessage(String key, Object... args);
    void clearScreen();
    int promptPlayer(String promptKey);
    Set<Integer> promptExpansionPackNumbers();
    void changeLanguage(String language, String country);
    default void initialize() {
    }
    default void shutdown() {
    }
}