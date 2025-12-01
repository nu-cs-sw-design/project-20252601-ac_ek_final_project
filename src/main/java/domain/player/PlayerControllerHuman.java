package domain.player;

import domain.cards.Card;
import domain.cards.implementations.CatCard;
import domain.cards.implementations.FeralCatCard;
import domain.game.Action;
import domain.game.GameEngine;
import ui.CardInfo;
import ui.GameUI;

import java.util.ArrayList;
import java.util.List;

public class PlayerControllerHuman implements PlayerController {
    private static final int YES = 1;
    private final GameUI userInterface;

    public PlayerControllerHuman(GameUI userInterface) {
        final GameUI realUI = userInterface;
        this.userInterface = new GameUI() {
            @Override
            public void displayMessage(String key) { realUI.displayMessage(key); }
            @Override
            public void displayFormattedMessage(String key, Object... args) { realUI.displayFormattedMessage(key, args); }
            @Override
            public void clearScreen() { realUI.clearScreen(); }
            @Override
            public int promptPlayer(String promptKey) { return realUI.promptPlayer(promptKey); }
            @Override
            public java.util.Set<Integer> promptExpansionPackNumbers() { return realUI.promptExpansionPackNumbers(); }
            @Override
            public void changeLanguage(String language, String country) { realUI.changeLanguage(language, country); }
        };
    }

    @Override
    public Action getTurnAction(GameEngine engine) {
        Player currentPlayer = engine.getCurrentPlayer();
        List<Card> hand = engine.getPlayerManager().getHand(currentPlayer);
        
        List<CardInfo> cardInfos = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            boolean playable = isCardPlayable(card, hand);
            cardInfos.add(new CardInfo(i, card.getName(), playable));
        }
        
        int result = userInterface.promptTurnAction(cardInfos);
        
        if (result >= 0) {
            return Action.playCard(result);
        }
        return Action.pass();
    }
    
    private boolean isCardPlayable(Card card, List<Card> hand) {
        String cardName = card.getName();
        
        if (cardName.equals("Defuse") || cardName.equals("Nope") || cardName.equals("Streaking Kitten")) {
            return false;
        }
        
        if (card instanceof CatCard) {
            return hasTwoOfName(hand, cardName);
        }
        
        if (card instanceof FeralCatCard) {
            if (hasTwoOfName(hand, "Feral Cat")) {
                return true;
            }
            for (CatCard.CatCardType catType : CatCard.CatCardType.values()) {
                if (hasCardWithName(hand, catType.cardName())) {
                    return true;
                }
            }
            return false;
        }
        
        return true;
    }
    
    private boolean hasTwoOfName(List<Card> hand, String cardName) {
        int count = 0;
        for (Card c : hand) {
            if (c.getName().equals(cardName)) {
                count++;
                if (count >= 2) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean hasCardWithName(List<Card> hand, String cardName) {
        for (Card c : hand) {
            if (c.getName().equals(cardName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Action getNopeAction(GameEngine engine) {
        int wantsToPlay = userInterface.promptPlayer("chooseNope");
        if (wantsToPlay == YES) {
            return Action.nope();
        }
        return Action.pass();
    }

    @Override
    public int chooseIndex(GameEngine engine, int max) {
        int deckSize = engine.getDeckSize();
        while (true) {
            int index = userInterface.promptPlayer("whereToInsert");
            if (index >= 0 && index <= deckSize) {
                return index;
            }
            userInterface.displayMessage("indexOutOfBounds");
            userInterface.displayMessage("tryAgain");
        }
    }

    @Override
    public int chooseTargetPlayer(GameEngine engine) {
        Player currentPlayer = engine.getCurrentPlayer();
        List<Player> players = engine.getPlayers();
        
        while (true) {
            int targetId = userInterface.promptPlayer("targetPlayerId");
            
            if (targetId == currentPlayer.getId()) {
                userInterface.displayMessage("chosenSelfError");
                userInterface.displayMessage("tryAgain");
                continue;
            }
            
            boolean validPlayer = false;
            for (Player player : players) {
                if (player.getId() == targetId) {
                    validPlayer = true;
                    break;
                }
            }
            
            if (validPlayer) {
                return targetId;
            }
            
            userInterface.displayMessage("invalidPlayerID");
            userInterface.displayMessage("tryAgain");
        }
    }

    @Override
    public int chooseCardIndex(GameEngine engine) {
        Player currentPlayer = engine.getCurrentPlayer();
        int handSize = engine.getPlayerManager().getHandCount(currentPlayer);
        
        while (true) {
            int cardIndex = userInterface.promptPlayer("chooseCardPrompt");
            if (cardIndex >= 0 && cardIndex < handSize) {
                return cardIndex;
            }
            userInterface.displayMessage("invalidCardIndex");
            userInterface.displayMessage("tryAgain");
        }
    }

    @Override
    public int getDecision(GameEngine engine, String prompt) {
        switch (prompt) {
            case "targetPlayerId":
            case "playerID":
            case "playerIDCurse":
                return getValidatedTargetPlayer(engine, prompt);
            case "whereToInsert":
                return getValidatedIndex(engine);
            case "chooseCardPrompt":
            case "takeCard":
            case "chosenPlayerCardIndex":
            case "targetCardIndex":
                return getValidatedCardIndex(engine, prompt);
            default:
                return userInterface.promptPlayer(prompt);
        }
    }

    private int getValidatedTargetPlayer(GameEngine engine, String prompt) {
        Player currentPlayer = engine.getCurrentPlayer();
        List<Player> players = engine.getPlayers();
        
        while (true) {
            int targetId = userInterface.promptPlayer(prompt);
            
            if (targetId == currentPlayer.getId()) {
                userInterface.displayMessage("chosenSelfError");
                userInterface.displayMessage("tryAgain");
                continue;
            }
            
            boolean validPlayer = false;
            for (Player player : players) {
                if (player.getId() == targetId) {
                    validPlayer = true;
                    break;
                }
            }
            
            if (validPlayer) {
                return targetId;
            }
            
            userInterface.displayMessage("invalidPlayerID");
            userInterface.displayMessage("tryAgain");
        }
    }

    private int getValidatedIndex(GameEngine engine) {
        int deckSize = engine.getDeckSize();
        while (true) {
            int index = userInterface.promptPlayer("whereToInsert");
            if (index >= 0 && index <= deckSize) {
                return index;
            }
            userInterface.displayMessage("indexOutOfBounds");
            userInterface.displayMessage("tryAgain");
        }
    }

    private int getValidatedCardIndex(GameEngine engine, String prompt) {
        Player currentPlayer = engine.getCurrentPlayer();
        int handSize = engine.getPlayerManager().getHandCount(currentPlayer);
        
        while (true) {
            int cardIndex = userInterface.promptPlayer(prompt);
            if (cardIndex >= 0 && cardIndex < handSize) {
                return cardIndex;
            }
            userInterface.displayMessage("invalidCardIndex");
            userInterface.displayMessage("tryAgain");
        }
    }
}