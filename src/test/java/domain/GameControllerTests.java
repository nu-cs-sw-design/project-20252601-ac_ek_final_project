package domain;

import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

public class GameControllerTests {
    @Test
    void testStartGameWith1Player() {
        UI mockUI = EasyMock.createMock(UI.class);
        Game mockGame = EasyMock.createMock(Game.class);

        EasyMock.expect(mockUI.promptPlayer("enterNumPlayers")).andReturn(1);
        mockUI.displayMessage("invalidNumPlayers");
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockUI.promptPlayer("enterNumPlayers")).andReturn(2);
        mockUI.displayMessage("welcomeMessage");
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockUI.promptPlayer("language")).andReturn(0);

        EasyMock.expect(mockGame.isGameOver()).andReturn(true);

        mockUI.displayMessage("gameOver");
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockUI, mockGame);

        GameController controller = new GameController(mockUI);
        controller.setGame(mockGame);

        controller.startGame();

        EasyMock.verify(mockUI, mockGame);
    }

    @Test
    void testStartGameWith2Players() {
        UI mockUI = EasyMock.createMock(UI.class);
        Game mockGame = EasyMock.createMock(Game.class);

        EasyMock.expect(mockUI.promptPlayer("enterNumPlayers")).andReturn(2);
        mockUI.displayMessage("welcomeMessage");
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockUI.promptPlayer("language")).andReturn(0);
        EasyMock.expect(mockGame.isGameOver()).andReturn(true);
        mockUI.displayMessage("gameOver");
        EasyMock.expectLastCall().once();
        EasyMock.replay(mockUI, mockGame);

        GameController controller = new GameController(mockUI);
        controller.setGame(mockGame);

        controller.startGame();

        EasyMock.verify(mockUI, mockGame);
    }

    @Test
    void testStartGameWith10Players() {
        UI mockUI = EasyMock.createMock(UI.class);
        Game mockGame = EasyMock.createMock(Game.class);

        EasyMock.expect(mockUI.promptPlayer("enterNumPlayers")).andReturn(10);
        mockUI.displayMessage("welcomeMessage");
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockGame.isGameOver()).andReturn(true);
        mockUI.displayMessage("gameOver");
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockUI.promptPlayer("language")).andReturn(1);
        mockUI.changeLanguage("fr", "Fr");
        EasyMock.expectLastCall();
        EasyMock.replay(mockUI, mockGame);

        GameController controller = new GameController(mockUI);
        controller.setGame(mockGame);

        controller.startGame();

        EasyMock.verify(mockUI, mockGame);
    }

    @Test
    void testStartGameWith11Players() {
        UI mockUI = EasyMock.createMock(UI.class);
        Game mockGame = EasyMock.createMock(Game.class);

        EasyMock.expect(mockUI.promptPlayer("enterNumPlayers")).andReturn(11);
        mockUI.displayMessage("invalidNumPlayers");
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockUI.promptPlayer("enterNumPlayers")).andReturn(2);
        mockUI.displayMessage("welcomeMessage");
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockUI.promptPlayer("language")).andReturn(0);
        EasyMock.expect(mockGame.isGameOver()).andReturn(true);

        mockUI.displayMessage("gameOver");
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockUI, mockGame);

        GameController controller = new GameController(mockUI);
        controller.setGame(mockGame);

        controller.startGame();

        EasyMock.verify(mockUI, mockGame);
    }
}