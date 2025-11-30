package ui;

import java.util.List;
import java.util.Set;

public interface GameUI {
    void displayMessage(String key);
    void displayFormattedMessage(String key, Object... args);
    void clearScreen();
    int promptPlayer(String promptKey);
    Set<Integer> promptExpansionPackNumbers();
    void changeLanguage(String language, String country);
    
    default int promptTurnAction(List<CardInfo> cards) {
        int playCardChoice = promptPlayer("playCardPrompt");
        if (playCardChoice == 1) {
            return promptPlayer("chooseCardPrompt");
        }
        return -1;
    }
    
    default void initialize() {
    }
    default void shutdown() {
    }
}