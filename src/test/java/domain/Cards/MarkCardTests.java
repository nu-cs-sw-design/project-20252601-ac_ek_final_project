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

public class MarkCardTests {
    @Test
    public void testPlayCard_Target5thPlayerChoosingRandomCard() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player targetPlayer = EasyMock.createMock(Player.class);
        Card targetCard = EasyMock.createMock(Card.class);
        int currentPlayerId = 0;
        int targetId = 5;
        int targetCardIndex = 0;
        int numPlayers = 5;
        int numCards = 4;
        MarkCard markCard = new MarkCard();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(i == targetId ? targetPlayer : EasyMock.createMock(Player.class));
        }
        ArrayList<Card> targetPlayerHand = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            targetPlayerHand.add(i == targetCardIndex ? targetCard : EasyMock.createMock(Card.class));
        }
        ArrayList<Card> targetPlayerVisibleHand = new ArrayList<>();

        int numberOfVisibleCards = 1;

        ui.displayMessage("markCard");
        EasyMock.expectLastCall();
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(targetId);
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(ui.promptPlayer("targetCardIndex")).andReturn(targetCardIndex);
        EasyMock.expect(game.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(game.getPlayer(targetId)).andReturn(targetPlayer).anyTimes();
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerId).anyTimes();
        EasyMock.expect(targetPlayer.getHand()).andReturn(targetPlayerHand).anyTimes();
        EasyMock.expect(targetPlayer.chooseCard(targetCardIndex)).andReturn(targetCard).anyTimes();
        EasyMock.expect(targetPlayer.getVisibleHand()).andReturn(targetPlayerVisibleHand).anyTimes();
        EasyMock.expect(targetPlayer.addVisibleCard(targetCard)).andReturn(numberOfVisibleCards);
        game.setPlayer(targetPlayer);
        EasyMock.expectLastCall();
        int cardIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(markCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, ui, targetPlayer, targetCard, currentPlayer);

        markCard.playCard(game, ui);
        EasyMock.verify(game, ui, targetPlayer, targetCard, currentPlayer);
    }

    @Test
    public void testPlayCard_TargetInvalidPlayer(){
        String expectedMsg = "invalidPlayerID";
        Game game = EasyMock.createMock(Game.class);
        UI ui = EasyMock.createMock(UI.class);
        int targetPlayerId = 6;
        int numPlayers = 5;
        MarkCard markCard = new MarkCard();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(EasyMock.createMock(Player.class));
        }

        ui.displayMessage("markCard");
        EasyMock.expectLastCall();
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(targetPlayerId);
        EasyMock.expect(game.getPlayers()).andReturn(players);

        EasyMock.replay(game, ui);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> markCard.playCard(game, ui));
        assertEquals(expectedMsg, exception.getMessage());

        EasyMock.verify(game, ui);
    }

    @Test
    public void testPlayCard_TargetInvalidPlayer_ID0(){
        String expectedMsg = "invalidPlayerID";
        Game game = EasyMock.createMock(Game.class);
        UI ui = EasyMock.createMock(UI.class);
        int targetPlayerId = 0;
        MarkCard markCard = new MarkCard();

        ui.displayMessage("markCard");
        EasyMock.expectLastCall();
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(targetPlayerId);

        EasyMock.replay(game, ui);
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> markCard.playCard(game, ui));
        assertEquals(expectedMsg, exception.getMessage());

        EasyMock.verify(game, ui);
    }

    @Test
    public void testPlayCard_Target1stPlayerChoosingRandomCard() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player targetPlayer = EasyMock.createMock(Player.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Card targetCard = EasyMock.createMock(Card.class);
        int currentPlayerId = 2;
        int targetId = 1;
        int targetCardIndex = 3;
        int numPlayers = 5;
        int numCards = 4;
        MarkCard markCard = new MarkCard();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(i == targetId ? targetPlayer : EasyMock.createMock(Player.class));
        }
        ArrayList<Card> targetPlayerHand = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            targetPlayerHand.add(i == targetCardIndex ? targetCard : EasyMock.createMock(Card.class));
        }
        ArrayList<Card> targetPlayerVisibleHand = new ArrayList<>();
        int numberOfVisibleCards = 1;

        ui.displayMessage("markCard");
        EasyMock.expectLastCall();
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(targetId);
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(ui.promptPlayer("targetCardIndex")).andReturn(targetCardIndex);
        EasyMock.expect(game.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(game.getPlayer(targetId)).andReturn(targetPlayer).anyTimes();
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerId).anyTimes();
        EasyMock.expect(targetPlayer.getHand()).andReturn(targetPlayerHand).anyTimes();
        EasyMock.expect(targetPlayer.chooseCard(targetCardIndex)).andReturn(targetCard).anyTimes();
        EasyMock.expect(targetPlayer.getVisibleHand()).andReturn(targetPlayerVisibleHand).anyTimes();
        EasyMock.expect(targetPlayer.addVisibleCard(targetCard)).andReturn(numberOfVisibleCards);
        game.setPlayer(targetPlayer);
        EasyMock.expectLastCall();
        int cardIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(markCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, ui, targetPlayer, targetCard, currentPlayer);

        markCard.playCard(game, ui);
        EasyMock.verify(game, ui, targetPlayer, targetCard, currentPlayer);
    }

    @Test
    public void testPlayCard_TargetYourselfError() {
        String expectedMsg = "chosenSelfError";
        Game game = EasyMock.createMock(Game.class);
        UI ui = EasyMock.createMock(UI.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player targetPlayer = currentPlayer;
        int currentPlayerId = 1;
        int targetId = 1;
        int numPlayers = 5;
        MarkCard markCard = new MarkCard();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(i == targetId ? targetPlayer : EasyMock.createMock(Player.class));
        }

        ui.displayMessage("markCard");
        EasyMock.expectLastCall();
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(targetId);
        EasyMock.expect(game.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(game.getPlayer(targetId)).andReturn(targetPlayer).anyTimes();
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerId).anyTimes();

        EasyMock.replay(game, ui, currentPlayer);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> markCard.playCard(game, ui));
        assertEquals(expectedMsg, exception.getMessage());

        EasyMock.verify(game, ui, currentPlayer);
    }

    @Test
    public void testPlayCard_Target1stPlayerChoosingInvalidCard() {
        String expectedMsg = "invalidCardIndex";
        Game game = EasyMock.createMock(Game.class);

        UI ui = EasyMock.createMock(UI.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player targetPlayer = EasyMock.createMock(Player.class);
        int currentPlayerId = 2;
        int targetId = 1;
        int targetCardIndex = 4;
        int numPlayers = 5;
        int numCards = 4;
        MarkCard markCard = new MarkCard();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(i == targetId ? targetPlayer : EasyMock.createMock(Player.class));
        }
        ArrayList<Card> targetPlayerHand = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            targetPlayerHand.add(EasyMock.createMock(Card.class));
        }

        ui.displayMessage("markCard");
        EasyMock.expectLastCall();
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(targetId);
        EasyMock.expect(ui.promptPlayer("targetCardIndex")).andReturn(targetCardIndex);
        EasyMock.expect(game.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(game.getPlayer(targetId)).andReturn(targetPlayer).anyTimes();
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerId).anyTimes();
        EasyMock.expect(targetPlayer.getHand()).andReturn(targetPlayerHand).anyTimes();

        EasyMock.replay(game, ui, targetPlayer, currentPlayer);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> markCard.playCard(game, ui));
        assertEquals(expectedMsg, exception.getMessage());

        EasyMock.verify(game, ui, targetPlayer, currentPlayer);
    }

    @Test
    public void testPlayCard_Target1stPlayerChoosingInvalidCard_negativeIndex() {
        String expectedMsg = "invalidCardIndex";
        Game game = EasyMock.createMock(Game.class);

        UI ui = EasyMock.createMock(UI.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player targetPlayer = EasyMock.createMock(Player.class);
        int currentPlayerId = 2;
        int targetId = 1;
        int targetCardIndex = -1;
        int numPlayers = 5;
        int numCards = 4;
        MarkCard markCard = new MarkCard();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(i == targetId ? targetPlayer : EasyMock.createMock(Player.class));
        }
        ArrayList<Card> targetPlayerHand = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            targetPlayerHand.add(EasyMock.createMock(Card.class));
        }

        ui.displayMessage("markCard");
        EasyMock.expectLastCall();
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(targetId);
        EasyMock.expect(ui.promptPlayer("targetCardIndex")).andReturn(targetCardIndex);
        EasyMock.expect(game.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(game.getPlayer(targetId)).andReturn(targetPlayer).anyTimes();
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerId).anyTimes();
        EasyMock.expect(targetPlayer.getHand()).andReturn(targetPlayerHand).anyTimes();

        EasyMock.replay(game, ui, targetPlayer, currentPlayer);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> markCard.playCard(game, ui));
        assertEquals(expectedMsg, exception.getMessage());

        EasyMock.verify(game, ui, targetPlayer, currentPlayer);
    }

    @Test
    public void testPlayCard_Target1stPlayerChoosingVisibleCard() {
        String expectedMsg = "alreadyVisible";
        Game game = EasyMock.createMock(Game.class);

        UI ui = EasyMock.createMock(UI.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player targetPlayer = EasyMock.createMock(Player.class);
        Card targetCard = EasyMock.createMock(Card.class);
        int targetId = 1;
        int currentPlayerId = 2;
        int targetCardIndex = 2;
        int numPlayers = 5;
        int numCards = 4;
        MarkCard markCard = new MarkCard();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(i == targetId ? targetPlayer : EasyMock.createMock(Player.class));
        }
        ArrayList<Card> targetPlayerHand = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            targetPlayerHand.add(i == targetCardIndex ? targetCard : EasyMock.createMock(Card.class));
        }
        ArrayList<Card> targetPlayerVisibleHand = new ArrayList<>();
        targetPlayerVisibleHand.add(targetCard);

        ui.displayMessage("markCard");
        EasyMock.expectLastCall();
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(targetId);
        EasyMock.expect(ui.promptPlayer("targetCardIndex")).andReturn(targetCardIndex);
        EasyMock.expect(game.getPlayers()).andReturn(players).anyTimes();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer).anyTimes();
        EasyMock.expect(game.getPlayer(targetId)).andReturn(targetPlayer).anyTimes();
        EasyMock.expect(currentPlayer.getId()).andReturn(currentPlayerId).anyTimes();
        EasyMock.expect(targetPlayer.getHand()).andReturn(targetPlayerHand).anyTimes();
        EasyMock.expect(targetPlayer.chooseCard(targetCardIndex)).andReturn(targetCard).anyTimes();
        EasyMock.expect(targetPlayer.getVisibleHand()).andReturn(targetPlayerVisibleHand).anyTimes();

        EasyMock.replay(game, ui, targetPlayer, currentPlayer);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> markCard.playCard(game, ui));
        assertEquals(expectedMsg, exception.getMessage());

        EasyMock.verify(game, ui, targetPlayer, currentPlayer);
    }
}