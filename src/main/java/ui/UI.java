package ui;

import java.text.MessageFormat;
import java.util.*;

public class UI {
    private Locale locale;
    private ResourceBundle resourceBundle;

    public UI() {
        this.locale = new Locale("en", "US");
        this.resourceBundle = ResourceBundle.getBundle("messages", locale);
    }

    public int promptPlayer(String key) {
        String message = this.resourceBundle.getString(key);
        Scanner scanner = new Scanner(System.in, "UTF-8");
        int userInput = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.println(message);
                userInput = scanner.nextInt();
                validInput = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input - please enter an integer");
                scanner.nextLine();
            }
        }
        return userInput;
    }

    public void displayMessage(String key) {
        String message = this.resourceBundle.getString(key);
        System.out.println(message);
    }

    public void changeLanguage(String language, String country) {
        this.locale = new Locale(language, country);
        this.resourceBundle = ResourceBundle.getBundle("messages", locale);
    }

    public void displayFormattedMessage(String key, Object... args) {
        String pattern = resourceBundle.getString(key);
        String message = MessageFormat.format(pattern, args);
        System.out.println(message);
    }

    public void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (java.io.IOException | InterruptedException e) {
            // Fallback: print multiple newlines if clearing fails
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    public Set<Integer> promptExpansionPackNumbers() {
        Set<Integer> selections = new HashSet<>();
        Scanner scanner = new Scanner(System.in, "UTF-8");
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
}