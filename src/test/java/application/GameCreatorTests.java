package application;

import domain.game.GameEngine;
import domain.player.Player;
import ui.GameUI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameCreatorTests {
    private GameCreator gameCreator;
    private GameEngine mockGameEngine;
    private GameUI mockGameUI;

    @BeforeEach
    public void setUp() {
        mockGameEngine = EasyMock.createMock(GameEngine.class);
        mockGameUI = EasyMock.createMock(GameUI.class);
        gameCreator = new GameCreator(mockGameEngine, mockGameUI);
    }

    @Test
    public void testConstructor_createsGameCreator() {
        assertNotNull(gameCreator);
    }

    @Test
    public void testConstructor_withNullGameEngine() {
        GameCreator gc = new GameCreator(null, mockGameUI);
        assertNotNull(gc);
    }

    @Test
    public void testConstructor_withNullUI() {
        GameCreator gc = new GameCreator(mockGameEngine, null);
        assertNotNull(gc);
    }

    @Test
    public void testStartGame_immediateGameOver_withWinner() {
        Player winner = new Player(1, new ArrayList<>());
        
        EasyMock.expect(mockGameEngine.isGameOver()).andReturn(true);
        mockGameUI.displayMessage("gameOver");
        EasyMock.expectLastCall();
        EasyMock.expect(mockGameEngine.getWinner()).andReturn(winner);
        mockGameUI.displayFormattedMessage("winner", 1);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockGameEngine, mockGameUI);
        
        gameCreator.startGame();
        
        EasyMock.verify(mockGameEngine, mockGameUI);
    }

    @Test
    public void testStartGame_immediateGameOver_noWinner() {
        EasyMock.expect(mockGameEngine.isGameOver()).andReturn(true);
        mockGameUI.displayMessage("gameOver");
        EasyMock.expectLastCall();
        EasyMock.expect(mockGameEngine.getWinner()).andReturn(null);
        
        EasyMock.replay(mockGameEngine, mockGameUI);
        
        gameCreator.startGame();
        
        EasyMock.verify(mockGameEngine, mockGameUI);
    }

    @Test
    public void testStartGame_oneTurnBeforeGameOver() {
        Player winner = new Player(2, new ArrayList<>());
        
        EasyMock.expect(mockGameEngine.isGameOver()).andReturn(false);
        mockGameEngine.takeTurn();
        EasyMock.expectLastCall();
        EasyMock.expect(mockGameEngine.isGameOver()).andReturn(true);
        mockGameUI.displayMessage("gameOver");
        EasyMock.expectLastCall();
        EasyMock.expect(mockGameEngine.getWinner()).andReturn(winner);
        mockGameUI.displayFormattedMessage("winner", 2);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockGameEngine, mockGameUI);
        
        gameCreator.startGame();
        
        EasyMock.verify(mockGameEngine, mockGameUI);
    }

    @Test
    public void testStartGame_multipleTurnsBeforeGameOver() {
        Player winner = new Player(3, new ArrayList<>());
        
        EasyMock.expect(mockGameEngine.isGameOver()).andReturn(false);
        mockGameEngine.takeTurn();
        EasyMock.expectLastCall();
        EasyMock.expect(mockGameEngine.isGameOver()).andReturn(false);
        mockGameEngine.takeTurn();
        EasyMock.expectLastCall();
        EasyMock.expect(mockGameEngine.isGameOver()).andReturn(false);
        mockGameEngine.takeTurn();
        EasyMock.expectLastCall();
        EasyMock.expect(mockGameEngine.isGameOver()).andReturn(true);
        mockGameUI.displayMessage("gameOver");
        EasyMock.expectLastCall();
        EasyMock.expect(mockGameEngine.getWinner()).andReturn(winner);
        mockGameUI.displayFormattedMessage("winner", 3);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockGameEngine, mockGameUI);
        
        gameCreator.startGame();
        
        EasyMock.verify(mockGameEngine, mockGameUI);
    }

    @Test
    public void testStartGame_winnerWithDifferentId() {
        Player winner = new Player(5, new ArrayList<>());
        
        EasyMock.expect(mockGameEngine.isGameOver()).andReturn(true);
        mockGameUI.displayMessage("gameOver");
        EasyMock.expectLastCall();
        EasyMock.expect(mockGameEngine.getWinner()).andReturn(winner);
        mockGameUI.displayFormattedMessage("winner", 5);
        EasyMock.expectLastCall();
        
        EasyMock.replay(mockGameEngine, mockGameUI);
        
        gameCreator.startGame();
        
        EasyMock.verify(mockGameEngine, mockGameUI);
    }

    @Test
    public void testStartGame_displaysGameOverMessage() {
        EasyMock.expect(mockGameEngine.isGameOver()).andReturn(true);
        mockGameUI.displayMessage("gameOver");
        EasyMock.expectLastCall();
        EasyMock.expect(mockGameEngine.getWinner()).andReturn(null);
        
        EasyMock.replay(mockGameEngine, mockGameUI);
        
        gameCreator.startGame();
        
        EasyMock.verify(mockGameEngine, mockGameUI);
    }

    @Test
    public void testStartGame_gameLoopCallsTakeTurn() {
        EasyMock.expect(mockGameEngine.isGameOver()).andReturn(false);
        mockGameEngine.takeTurn();
        EasyMock.expectLastCall();
        EasyMock.expect(mockGameEngine.isGameOver()).andReturn(true);
        mockGameUI.displayMessage("gameOver");
        EasyMock.expectLastCall();
        EasyMock.expect(mockGameEngine.getWinner()).andReturn(null);
        
        EasyMock.replay(mockGameEngine, mockGameUI);
        
        gameCreator.startGame();
        
        EasyMock.verify(mockGameEngine, mockGameUI);
    }
}