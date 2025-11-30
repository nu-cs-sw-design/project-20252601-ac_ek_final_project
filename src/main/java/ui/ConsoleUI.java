package ui;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.*;

public class ConsoleUI implements GameUI {    
    private Locale locale;
    private ResourceBundle resourceBundle;
    private final Scanner scanner;

    public ConsoleUI() {
        this(System.in);
    }

    public ConsoleUI(InputStream in) {
        this.locale = new Locale("en", "US");
        this.resourceBundle = ResourceBundle.getBundle("messages", locale);
        this.scanner = new Scanner(in, "UTF-8");
    }

    @Override
    public void displayMessage(String key) {
        String message = resourceBundle.getString(key);
        System.out.println(message);
    }

    @Override
    public void displayFormattedMessage(String key, Object... args) {
        String pattern = resourceBundle.getString(key);
        String message = MessageFormat.format(pattern, args);
        System.out.println(message);
    }

    @Override
    public void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    @Override
    public int promptPlayer(String promptKey) {
        String message = resourceBundle.getString(promptKey);
        while (true) {
            try {
                System.out.println(message);
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input - please enter an integer");
                scanner.nextLine();
            }
        }
    }

    @Override
    public Set<Integer> promptExpansionPackNumbers() {
        Set<Integer> selections = new HashSet<>();
        boolean validInput = false;
        
        while (!validInput) {
            try {
                displayMessage("expansionPackPrompt");
                String input = scanner.nextLine().trim();
                
                if (input.equals("0") || input.isEmpty()) {
                    validInput = true;
                    displayMessage("noExpansions");
                    break;
                }
                
                String[] parts = input.split(",");
                for (String part : parts) {
                    int choice = Integer.parseInt(part.trim());
                    selections.add(choice);
                }
                validInput = true;
                
            } catch (NumberFormatException e) {
                displayMessage("invalidExpansionSelection");
                selections.clear();
            }
        }
        return selections;
    }

    @Override
    public void changeLanguage(String language, String country) {
        this.locale = new Locale(language, country);
        this.resourceBundle = ResourceBundle.getBundle("messages", locale);
    }
    
    @Override
    public int promptTurnAction(List<CardInfo> cards) {
        System.out.println(resourceBundle.getString("currentHand"));
        
        for (CardInfo card : cards) {
            String playableMarker = card.isPlayable() ? "[PLAYABLE]" : "[------]";
            System.out.printf("  %s [%d] %s%n", playableMarker, card.index(), card.name());
        }
        
        System.out.println();
        System.out.println("Enter card index to play, or -1 to draw a card:");
        
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                
                if (input == -1) {
                    return -1;
                }
                
                for (CardInfo card : cards) {
                    if (card.index() == input) {
                        if (card.isPlayable()) {
                            return input;
                        } else {
                            System.out.println("That card cannot be played. Choose a playable card or -1 to draw:");
                            break;
                        }
                    }
                }
                
                System.out.println("Invalid card index. Try again:");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input - please enter an integer");
                scanner.nextLine();
            }
        }
    }
}