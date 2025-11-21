package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TargetedAttackCardTests {
    @Test
    public void testPlayCard_currentPlayerNoTurnsTargets5thPlayer_returnsTargetPlayer2Turns(){
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player targetPlayer = EasyMock.createMock(Player.class);
        int currentPlayerId = 0;
        int currentPlayerExistingTurns = 0;
        int targetPlayerNewTurns = 2;
        int numPlayers = 5;
        TargetedAttackCard targetedAttackCard = new TargetedAttackCard();

        int targetId = 5;
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(i == targetId ? targetPlayer : EasyMock.createMock(Player.class));
        }

        ui.displayMessage("targetedAttackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerId).anyTimes();
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(targetId);
        EasyMock.expect(game.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(currentPlayer.getNumberOfTurns()).andReturn(currentPlayerExistingTurns).anyTimes();
        int cardIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(targetedAttackCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();
        game.setPlayerTurns(targetId, targetPlayerNewTurns);
        game.setCurrentPlayerTurns(currentPlayerExistingTurns);
        EasyMock.expect(targetPlayer.getNumberOfTurns()).andReturn(targetPlayerNewTurns);


        EasyMock.replay(game, ui, currentPlayer, targetPlayer);

        targetedAttackCard.playCard(game, ui);
        assertEquals(targetPlayer.getNumberOfTurns(), targetPlayerNewTurns);
        EasyMock.verify(game, ui, currentPlayer, targetPlayer);
    }

    @Test
    public void testPlayCard_currentPlayerPlaysTargetAttackCardSelectsInvalidPlayer(){

        String expectedMsg = "invalidPlayerID";
        Game game = EasyMock.createMock(Game.class);

        UI ui = EasyMock.createMock(UI.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        TargetedAttackCard targetedAttackCard = new TargetedAttackCard();
        int targetId = 6;
        int numPlayers = 5;
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(EasyMock.createMock(Player.class));
        }

        ui.displayMessage("targetedAttackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(game.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(targetId);

        EasyMock.replay(game, ui);

        Exception excepetion = assertThrows(UnsupportedOperationException.class, () -> targetedAttackCard.playCard(game, ui));
        assertEquals(expectedMsg, excepetion.getMessage());
        EasyMock.verify(game, ui);
    }

    @Test
    public void testPlayCard_currentPlayerPlaysTargetAttackCardSelectsInvalidPlayer_ID0(){

        String expectedMsg = "invalidPlayerID";
        Game game = EasyMock.createMock(Game.class);

        UI ui = EasyMock.createMock(UI.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        TargetedAttackCard targetedAttackCard = new TargetedAttackCard();
        int targetId = 0;
        int numPlayers = 5;
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(EasyMock.createMock(Player.class));
        }

        ui.displayMessage("targetedAttackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(game.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(targetId);

        EasyMock.replay(game, ui);

        Exception excepetion = assertThrows(UnsupportedOperationException.class, () -> targetedAttackCard.playCard(game, ui));
        assertEquals(expectedMsg, excepetion.getMessage());
        EasyMock.verify(game, ui);
    }

    @Test
    public void testPlayCard_currentPlayerPlaysTargetAttackCardSelectsThemselfError(){

        String expectedMsg = "chosenSelfError";
        Game game = EasyMock.createMock(Game.class);

        UI ui = EasyMock.createMock(UI.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        TargetedAttackCard targetedAttackCard = new TargetedAttackCard();
        Player targetPlayer = currentPlayer;
        int targetId = 1;
        int numPlayers = 5;
        int currentPlayerId = 1;
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(i == targetId ? targetPlayer : EasyMock.createMock(Player.class));
        }

        ui.displayMessage("targetedAttackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(game.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(targetId);
        EasyMock.expect(game.getPlayer(targetId)).andReturn(targetPlayer).anyTimes();
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerId).anyTimes();

        EasyMock.replay(game, ui, currentPlayer);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> targetedAttackCard.playCard(game, ui));
        assertEquals(expectedMsg, exception.getMessage());
        EasyMock.verify(game, ui, currentPlayer);
    }

    @Test
    public void testPlayCard_currentPlayer1TurnTargets1stPlayer_returnsTargetPlayer2Turns(){
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player targetPlayer = EasyMock.createMock(Player.class);
        int currentPlayerExistingTurns = 1;
        int targetPlayerNewTurns = 2;
        int currentPlayerFinalNumTurns = 0;
        TargetedAttackCard targetedAttackCard = new TargetedAttackCard();

        int targetId = 1;
        int numPlayers = 5;
        int currentPlayerId = 2;
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(i == targetId ? targetPlayer : EasyMock.createMock(Player.class));
        }

        ui.displayMessage("targetedAttackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(targetId);
        EasyMock.expect(game.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(game.getPlayer(targetId)).andReturn(targetPlayer).anyTimes();
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerId);
        EasyMock.expect(currentPlayer.getNumberOfTurns()).andReturn(currentPlayerExistingTurns).anyTimes();
        int cardIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(targetedAttackCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();
        game.setPlayerTurns(targetId, targetPlayerNewTurns);
        EasyMock.expectLastCall();
        game.setCurrentPlayerTurns(currentPlayerFinalNumTurns);
        EasyMock.expectLastCall();
        EasyMock.expect(targetPlayer.getNumberOfTurns()).andReturn(targetPlayerNewTurns);

        EasyMock.replay(game, ui, currentPlayer, targetPlayer);

        targetedAttackCard.playCard(game, ui);
        assertEquals(targetPlayer.getNumberOfTurns(), targetPlayerNewTurns);
        EasyMock.verify(game, ui, currentPlayer, targetPlayer);
    }

    @Test
    public void testPlayCard_currentPlayer2TurnTargets1stPlayer_returnsTargetPlayer4Turns(){
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player targetPlayer = EasyMock.createMock(Player.class);
        int currentPlayerExistingTurns = 2;
        int targetPlayerNewTurns = 4;
        int currentPlayerFinalTurns = 0;
        TargetedAttackCard targetedAttackCard = new TargetedAttackCard();
        int targetId = 1;
        int numPlayers = 5;
        int currentPlayerId = 2;
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(i == targetId ? targetPlayer : EasyMock.createMock(Player.class));
        }

        ui.displayMessage("targetedAttackCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(targetId);
        EasyMock.expect(game.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(game.getPlayer(targetId)).andReturn(targetPlayer).anyTimes();
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerId);
        EasyMock.expect(currentPlayer.getNumberOfTurns()).andReturn(currentPlayerExistingTurns).anyTimes();
        int cardIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(targetedAttackCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();
        game.setPlayerTurns(targetId, targetPlayerNewTurns);
        EasyMock.expectLastCall();
        game.setCurrentPlayerTurns(currentPlayerFinalTurns);
        EasyMock.expectLastCall();
        EasyMock.expect(targetPlayer.getNumberOfTurns()).andReturn(targetPlayerNewTurns);

        EasyMock.replay(game, ui, currentPlayer, targetPlayer);

        targetedAttackCard.playCard(game, ui);
        assertEquals(targetPlayer.getNumberOfTurns(), targetPlayerNewTurns);
        EasyMock.verify(game, ui, currentPlayer, targetPlayer);
    }
}