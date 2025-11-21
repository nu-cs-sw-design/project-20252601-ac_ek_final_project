package domain.Cards;

import domain.Game;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DefuseCardTests {
    @Test
    public void testPlayCard_throwsException(){
        DefuseCard defuseCard = new DefuseCard();
        String expectedMsg = "cantPlayCard";
        Game game = EasyMock.createMock(Game.class);
        UI ui = EasyMock.createMock(UI.class);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {defuseCard.playCard(game, ui);});
        assertEquals(expectedMsg, exception.getMessage());
    }
}