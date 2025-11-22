package domain.Cards;

import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FavorCardTests {
    private static final int CHOSEN_CARD_INDEX = 1;
    private static final int NEW_HAND_SIZE = 8;

    @Test
    public void testPlayCard_givenPlayerSelectThemSelves_ThenThrowException() {
        int curPlayerID = 2;
        int selectedPlayerID = 2;
        String expectMsg = "chosenSelfError";

        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        FavorCard favorCard = new FavorCard();

        ui.displayMessage("favorCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(selectedPlayerID);
        EasyMock.expect(currentPlayer.getId()).andReturn(curPlayerID);

        EasyMock.replay(ui, game, currentPlayer);

        Exception exception = assertThrows(InputMismatchException.class, ()->{favorCard.playCard(game, ui);});
        assertEquals(expectMsg, exception.getMessage());
        EasyMock.verify(ui, game, currentPlayer);
    }

    @Test
    public void testPlayCard_givenPlayer3ChoosePlayer9AndAttackCardGiven_ThenCardAddedToHand() {
        int curPlayerID = 3;
        int selectedPlayerID = 9;

        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player chosenPlayer = EasyMock.createMock(Player.class);
        AttackCard attackCard = EasyMock.createMock(AttackCard.class);
        FavorCard favorCard = new FavorCard();

        ui.displayMessage("favorCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(selectedPlayerID);
        EasyMock.expect(currentPlayer.getId()).andReturn(curPlayerID);
        EasyMock.expect(game.getPlayer(selectedPlayerID)).andReturn(chosenPlayer);
        EasyMock.expect(ui.promptPlayer("chosenPlayerCardIndex")).andReturn(CHOSEN_CARD_INDEX);
        EasyMock.expect(chosenPlayer.chooseCard(CHOSEN_CARD_INDEX)).andReturn(attackCard);
        EasyMock.expect(chosenPlayer.removeCard(CHOSEN_CARD_INDEX)).andReturn(NEW_HAND_SIZE);
        game.setPlayer(chosenPlayer);
        EasyMock.expectLastCall();
        game.setPlayer(currentPlayer);
        EasyMock.expectLastCall();
        int cardIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(favorCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();
        EasyMock.expect(attackCard.getName()).andReturn("Attack");
        EasyMock.expect(currentPlayer.addCard(attackCard)).andReturn(NEW_HAND_SIZE);
        ui.displayMessage("addCard");
        EasyMock.expectLastCall();

        EasyMock.replay(ui, game, currentPlayer, chosenPlayer, attackCard);

        favorCard.playCard(game, ui);
        EasyMock.verify(ui, game, currentPlayer, chosenPlayer, attackCard);
    }

    @Test
    public void testPlayCard_givenPlayer9ChoosePlayer10AndExplodingCardGiven_ThenExplodingIsTriggered() {
        int curPlayerID = 3;
        int selectedPlayerID = 9;

        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player chosenPlayer = EasyMock.createMock(Player.class);
        ExplodingKittenCard explodingKittenCard = EasyMock.createMock(ExplodingKittenCard.class);
        FavorCard favorCard = new FavorCard();

        ui.displayMessage("favorCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(selectedPlayerID);
        EasyMock.expect(currentPlayer.getId()).andReturn(curPlayerID);
        EasyMock.expect(game.getPlayer(selectedPlayerID)).andReturn(chosenPlayer);
        EasyMock.expect(ui.promptPlayer("chosenPlayerCardIndex")).andReturn(CHOSEN_CARD_INDEX);
        EasyMock.expect(chosenPlayer.chooseCard(CHOSEN_CARD_INDEX)).andReturn(explodingKittenCard);
        EasyMock.expect(chosenPlayer.removeCard(CHOSEN_CARD_INDEX)).andReturn(NEW_HAND_SIZE);
        game.setPlayer(chosenPlayer);
        EasyMock.expectLastCall();
        EasyMock.expect(explodingKittenCard.getName()).andReturn("Exploding Kitten");
        explodingKittenCard.playCard(game, ui);
        EasyMock.expectLastCall();
        int cardIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(favorCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(ui, game, currentPlayer, chosenPlayer, explodingKittenCard);

        favorCard.playCard(game, ui);
        EasyMock.verify(ui, game, currentPlayer, chosenPlayer, explodingKittenCard);
    }

    @Test
    public void testPlayCard_givenPlayer10ChoosePlayer3AndAttackCardGiven_ThenCardAddedToHand() {
        int curPlayerID = 10;
        int selectedPlayerID = 3;

        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Player currentPlayer = EasyMock.createMock(Player.class);
        Player chosenPlayer = EasyMock.createMock(Player.class);
        AttackCard attackCard = EasyMock.createMock(AttackCard.class);
        FavorCard favorCard = new FavorCard();

        ui.displayMessage("favorCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(currentPlayer);
        EasyMock.expect(ui.promptPlayer("targetPlayerId")).andReturn(selectedPlayerID);
        EasyMock.expect(currentPlayer.getId()).andReturn(curPlayerID);
        EasyMock.expect(game.getPlayer(selectedPlayerID)).andReturn(chosenPlayer);
        EasyMock.expect(ui.promptPlayer("chosenPlayerCardIndex")).andReturn(CHOSEN_CARD_INDEX);
        EasyMock.expect(chosenPlayer.chooseCard(CHOSEN_CARD_INDEX)).andReturn(attackCard);
        EasyMock.expect(chosenPlayer.removeCard(CHOSEN_CARD_INDEX)).andReturn(NEW_HAND_SIZE);
        game.setPlayer(chosenPlayer);
        EasyMock.expectLastCall();
        game.setPlayer(currentPlayer);
        EasyMock.expectLastCall();
        int cardIndex = 0;
        EasyMock.expect(currentPlayer.hasCard(favorCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();
        EasyMock.expect(attackCard.getName()).andReturn("Attack");
        EasyMock.expect(currentPlayer.addCard(attackCard)).andReturn(NEW_HAND_SIZE);
        ui.displayMessage("addCard");
        EasyMock.expectLastCall();

        EasyMock.replay(ui, game, currentPlayer, chosenPlayer, attackCard);

        favorCard.playCard(game, ui);
        EasyMock.verify(ui, game, currentPlayer, chosenPlayer, attackCard);
    }
}