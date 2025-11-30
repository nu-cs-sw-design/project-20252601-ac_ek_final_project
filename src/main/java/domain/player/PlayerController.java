package domain.player;

import domain.game.Action;
import domain.game.GameEngine;

public interface PlayerController {
    Action getTurnAction(GameEngine engine);
    Action getNopeAction(GameEngine engine);
    
    int chooseIndex(GameEngine engine, int max);
    int chooseTargetPlayer(GameEngine engine);
    int chooseCardIndex(GameEngine engine);
    int getDecision(GameEngine engine, String prompt);
}
