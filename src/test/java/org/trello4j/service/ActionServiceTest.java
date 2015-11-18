/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trello4j.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.trello4j.TrelloImplTest;
import org.trello4j.model.Action;
import org.trello4j.service.impl.TrelloImpl;

/**
 *
 * @author Emanuel Batista da Silva Filho - https://github.com/emanuelbatista
 */
public class ActionServiceTest {
    
    private final ActionService actionService;

    public ActionServiceTest() {
        Trello trello=new TrelloImpl(TrelloImplTest.API_KEY,TrelloImplTest.API_TOKEN);
        this.actionService=trello.getActionService();
    }
    
     @Test
    public void shouldReturnAction() {
        // GIVEN
        String actionId = "564c6ab4bc33842f9df07263";

        // WHEN
        Action action = actionService.getAction(actionId);

        // THEN
        assertNotNull("Oops, action is null", action);
        assertEquals("Incorrect action id", actionId, action.getId());
        assertNotNull("Date not set", action.getDate());
        assertNotNull("idMemberCreator not set", action.getIdMemberCreator());

        assertNotNull("memberCreator not set", action.getMemberCreator());
        assertNotNull("memberCreator.id not set", action.getMemberCreator().getId());
        assertNotNull("memberCreator.username not set", action.getMemberCreator().getUsername());
        assertNotNull("memberCreator.fullName not set", action.getMemberCreator().getFullName());
        assertNotNull("memberCreator.initials not set", action.getMemberCreator().getInitials());

        assertNotNull("data not set", action.getData());
//        assertNotNull("data.text not set", action.getData().getCard());
        assertNotNull("data.board not set", action.getData().getBoard());
        assertNotNull("data.board.id not set", action.getData().getBoard().getId());
        assertNotNull("data.board.name not set", action.getData().getBoard().getName());
    }
}
