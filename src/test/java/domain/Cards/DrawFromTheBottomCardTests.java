package domain.Cards;

import domain.Deck;
import domain.Game;
import domain.Player;
import ui.UI;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DrawFromTheBottomCardTests {
    @Test
    public void testPlayCard_givenEmptyDeckAndHand_WhenDrawFromTheBottomCardIsPlayed_ThenThrowException() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        DrawFromTheBottomCard drawFromTheBottomCard = new DrawFromTheBottomCard();
        String expectedMsg = "Deck is Empty";

        ui.displayMessage("drawFromBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.drawBottomCard()).andThrow(new UnsupportedOperationException("Deck is Empty"));

        EasyMock.replay(game, deck, ui);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {drawFromTheBottomCard.playCard(game, ui);});
        assertEquals(expectedMsg, exception.getMessage());
        EasyMock.verify(game, deck, ui);
    }

    @Test
    public void testPlayCard_givenDeckWithAttackCardAndEmptyHand_WhenDrawFromTheBottomCardIsPlayed_ThenDeckIsEmptyAndHandHasAttack() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Player player = EasyMock.createMock(Player.class);
        AttackCard attackCard = EasyMock.createMock(AttackCard.class);
        DrawFromTheBottomCard drawFromTheBottomCard = new DrawFromTheBottomCard();

        int expectedNumCardsInHand = 1;
        int finalTurns = 0;

        ui.displayMessage("drawFromBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.drawBottomCard()).andReturn(attackCard);
        game.addToCurrentPlayer(attackCard);
        EasyMock.expectLastCall();
        game.setCurrentPlayerTurns(finalTurns);
        EasyMock.expectLastCall();
        game.setDeck(deck);
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(drawFromTheBottomCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, player, attackCard, ui);

        drawFromTheBottomCard.playCard(game, ui);
        EasyMock.verify(game, deck, player, attackCard, ui);
    }

    @Test
    public void testPlayCard_givenDeckWithAttackAndNope_HandWithAttack_WhenDrawFromBottomIsPlayed_ThenDeckHasAttackHandHasAttackAndNope() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Player player = EasyMock.createMock(Player.class);
        NopeCard nopeCard = EasyMock.createMock(NopeCard.class);
        DrawFromTheBottomCard drawFromTheBottomCard = new DrawFromTheBottomCard();

        int expectedNumCardsInHand = 2;
        int finalTurns = 0;

        ui.displayMessage("drawFromBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.drawBottomCard()).andReturn(nopeCard);
        game.addToCurrentPlayer(nopeCard);
        EasyMock.expectLastCall();
        game.setCurrentPlayerTurns(finalTurns);
        EasyMock.expectLastCall();
        game.setDeck(deck);
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(drawFromTheBottomCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, nopeCard, ui);

        drawFromTheBottomCard.playCard(game, ui);
        EasyMock.verify(game, deck, nopeCard, ui);
    }

    @Test
    public void testPlayCard_givenDeckAndHandWith2Attack1Skip_WhenDrawFromBottomCardIsPlayed_ThenDeckHasAttackAttackAndHandHasAttackAttackSkipSkip() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Player player = EasyMock.createMock(Player.class);
        SkipCard skipCard = EasyMock.createMock(SkipCard.class);
        DrawFromTheBottomCard drawFromTheBottomCard = new DrawFromTheBottomCard();

        int expectedNumCardsInHand = 4;
        int finalTurns = 0;

        ui.displayMessage("drawFromBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.drawBottomCard()).andReturn(skipCard);
        game.addToCurrentPlayer(skipCard);
        EasyMock.expectLastCall();
        game.setCurrentPlayerTurns(finalTurns);
        EasyMock.expectLastCall();
        game.setDeck(deck);
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(drawFromTheBottomCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, player, skipCard, ui);

        drawFromTheBottomCard.playCard(game, ui);
        EasyMock.verify(game, deck, player, skipCard, ui);
    }

    @Test
    public void testPlayCard_givenDeckWithAttackAndHandWithAttackNopeCards_WhenDrawFromBottomCardIsPlayed_ThenEmptyDeckAndHandHasAttackNopeAttack() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Player player = EasyMock.createMock(Player.class);
        AttackCard attackCard = EasyMock.createMock(AttackCard.class);
        DrawFromTheBottomCard drawFromTheBottomCard = new DrawFromTheBottomCard();

        int expectedNumCardsInHand = 3;
        int finalTurns = 0;

        ui.displayMessage("drawFromBottomCard");
        EasyMock.expectLastCall();
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.drawBottomCard()).andReturn(attackCard);
        game.addToCurrentPlayer(attackCard);
        EasyMock.expectLastCall();
        game.setCurrentPlayerTurns(finalTurns);
        EasyMock.expectLastCall();
        game.setDeck(deck);
        EasyMock.expectLastCall();
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        int cardIndex = 0;
        EasyMock.expect(player.hasCard(drawFromTheBottomCard.getName())).andReturn(cardIndex);
        game.removeCurrentPlayerCard(cardIndex);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, player, attackCard, ui);

        drawFromTheBottomCard.playCard(game, ui);
        EasyMock.verify(game, deck, player, attackCard, ui);
    }

    @Test
    public void testPlayCard_lineCoverage_isExploding() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Player player = EasyMock.createMock(Player.class);
        ExplodingKittenCard explodingKitten = EasyMock.createMock(ExplodingKittenCard.class);
        DrawFromTheBottomCard drawFromTheBottomCard = new DrawFromTheBottomCard();

        ui.displayMessage("drawFromBottomCard");
        EasyMock.expect(deck.drawBottomCard()).andReturn(explodingKitten);
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        explodingKitten.playCard(game, ui);
        EasyMock.expectLastCall();
        game.setCurrentPlayerTurns(0);
        game.setDeck(deck);
        EasyMock.expect(player.hasCard(drawFromTheBottomCard.getName())).andReturn(0);
        game.removeCurrentPlayerCard(0);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, player, explodingKitten, ui);

        drawFromTheBottomCard.playCard(game, ui);

        EasyMock.verify(game, deck, player, explodingKitten, ui);
    }

    @Test
    public void testPlayCard_lineCoverage_isImploding() {
        UI ui = EasyMock.createMock(UI.class);
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Player player = EasyMock.createMock(Player.class);
        ImplodingKittenCard implodingKittenCard = EasyMock.createMock(ImplodingKittenCard.class);
        DrawFromTheBottomCard drawFromTheBottomCard = new DrawFromTheBottomCard();

        ui.displayMessage("drawFromBottomCard");
        EasyMock.expect(deck.drawBottomCard()).andReturn(implodingKittenCard);
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        implodingKittenCard.playCard(game, ui);
        EasyMock.expectLastCall();
        game.setCurrentPlayerTurns(0);
        game.setDeck(deck);
        EasyMock.expect(player.hasCard(drawFromTheBottomCard.getName())).andReturn(0);
        game.removeCurrentPlayerCard(0);
        EasyMock.expectLastCall();

        EasyMock.replay(game, deck, player, implodingKittenCard, ui);

        drawFromTheBottomCard.playCard(game, ui);

        EasyMock.verify(game, deck, player, implodingKittenCard, ui);
    }
}
