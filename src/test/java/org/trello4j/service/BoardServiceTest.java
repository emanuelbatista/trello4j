/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trello4j.service;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

import org.trello4j.TrelloImplTest;
import org.trello4j.exception.TrelloException;
import org.trello4j.model.Action;
import org.trello4j.model.Board;
import org.trello4j.service.impl.TrelloImpl;

/**
 *
 * @author Emanuel Batista da Silva Filho - https://github.com/emanuelbatista
 */
public class BoardServiceTest {

    private final BoardService boardService;
    private final String BOARD_ID = "564c5e78a5ccfadecdc8c053";

    public BoardServiceTest() {
        this.boardService = new TrelloImpl(TrelloImplTest.API_KEY, TrelloImplTest.API_TOKEN).getBoardService();
    }

    @Test(expected = TrelloException.class)
    public void testInvalidObjectId() {
        // GIVEN		
        String boardId = "INVALID_ID";

        // WHEN
        Board board = boardService.getBoard(boardId);

        // THEN
        assertNull("Oops, board is null", board);
    }
    
    @Test
    public void testNotExistObjectId(){
          // GIVEN		
        String boardId = "00000000000000000000000c";

        // WHEN
        Board board = boardService.getBoard(boardId);

        // THEN
        assertNull("Oops, board is null", board);
    }
    
    @Test 
    public void testReturnActionByBoard(){
        // WHEN
        List<Action> actions = boardService.getActionsByBoard(BOARD_ID);

        // THEN
        assertTrue("Board should have at least one action", actions.size() > 0);
        assertEquals("Board id and action.data.board.id should be equal", BOARD_ID, actions.get(0).getData().getBoard().getId());
    }
    
    @Test
    public void testeReturnBoardPublic(){
        // WHEN
        Board board = boardService.getBoard(BOARD_ID);

        // THEN
        assertNotNull("Oops, board is null", board);
        assertEquals("Incorrect board id", BOARD_ID, board.getId());
        assertEquals("Incorrect name of board", "Teste Public", board.getName());
        assertEquals("Incorrect organization id", "564bbd27bbf576ccdf3a9a83", board.getIdOrganization());
        assertEquals("Incorrect url", "https://trello.com/b/SuLfRSRT/teste-public", board.getUrl());
        assertFalse("This should be an open board", board.isClosed());
        assertNotNull(board.getDesc());
        assertNotNull(board.getPrefs());
        assertEquals(Board.PERMISSION_TYPE.PUBLIC, board.getPrefs().getVoting());
    }
}
