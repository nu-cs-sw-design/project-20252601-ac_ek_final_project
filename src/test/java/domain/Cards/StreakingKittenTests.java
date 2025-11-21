package domain.Cards;

import domain.Game;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StreakingKittenTests {
    @Test
    public void testPlayCard_throwsException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        StreakingKittenCard streakingKittenCard = new StreakingKittenCard();
        String expectedMsg = "cantPlayCard";

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {streakingKittenCard.playCard(game, ui);});
        assertEquals(expectedMsg, exception.getMessage());
    }
}