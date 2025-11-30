package domain.player;

import domain.game.Action;
import domain.game.GameEngine;

public class PlayerControllerAI implements PlayerController {
    private static final int YES = 1;
    private final AIAgent aiAgent;

    public PlayerControllerAI() {
        this.aiAgent = new AIAgent();
    }

    @Override
    public Action getTurnAction(GameEngine engine) {
        int decision = aiAgent.getDecision("playCardPrompt", engine);
        if (decision == YES) {
            int cardIndex = aiAgent.getDecision("chooseCardPrompt", engine);
            return Action.playCard(cardIndex);
        }
        return Action.pass();
    }

    @Override
    public Action getNopeAction(GameEngine engine) {
        int decision = aiAgent.getDecision("chooseNope", engine);
        if (decision == YES) {
            return Action.nope();
        }
        return Action.pass();
    }

    @Override
    public int chooseIndex(GameEngine engine, int max) {
        return aiAgent.getDecision("whereToInsert", engine);
    }

    @Override
    public int chooseTargetPlayer(GameEngine engine) {
        return aiAgent.getDecision("targetPlayerId", engine);
    }

    @Override
    public int chooseCardIndex(GameEngine engine) {
        return aiAgent.getDecision("chooseCardPrompt", engine);
    }

    @Override
    public int getDecision(GameEngine engine, String prompt) {
        return aiAgent.getDecision(prompt, engine);
    }
}