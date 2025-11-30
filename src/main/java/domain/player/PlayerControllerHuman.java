package domain.player;

import domain.game.Action;
import domain.game.GameEngine;
import ui.GameUI;

import java.util.List;

public class PlayerControllerHuman implements PlayerController {
    private static final int YES = 1;
    private final GameUI userInterface;

    public PlayerControllerHuman(GameUI userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public Action getTurnAction(GameEngine engine) {
        int playCardChoice = userInterface.promptPlayer("playCardPrompt");
        if (playCardChoice == YES) {
            int cardIndex = userInterface.promptPlayer("chooseCardPrompt");
            return Action.playCard(cardIndex);
        }
        return Action.pass();
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