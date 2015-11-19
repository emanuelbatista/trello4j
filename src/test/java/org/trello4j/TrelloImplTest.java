package org.trello4j;

import org.trello4j.service.impl.TrelloImpl;
import org.trello4j.exception.TrelloException;
import org.junit.Test;
import org.trello4j.model.*;
import org.trello4j.model.Board.PERMISSION_TYPE;

import java.util.List;

import static org.junit.Assert.*;
import org.trello4j.service.ActionService;

/**
 * The Class TrelloImplTest.
 */
public class TrelloImplTest {

    public static final String API_KEY = "f9e147388c9b6b46bf4e32009f8cf567";
    public static final String API_TOKEN = "a90c3c4fd2062571b2a3db5b39c10a81402702aedb563ead9aefe13fa3b74ec8";

    @Test(expected = TrelloException.class)
    public void missingApiKey_shouldThrowException() {
        new TrelloImpl(null);
    }
    
    @Test
    public void shouldActionService(){
        
        //WHEN
        ActionService actionService=new TrelloImpl(API_KEY).getActionService();
        
        //THEN
        assertNotNull("ActionService is null",actionService);
        
    }

    @Test
    public void shouldReturnOrganization() {
        // GIVEN
        String organizationName = "teste21917343";

        // WHEN
        Organization org = new TrelloImpl(API_KEY, null).getOrganization(organizationName);

        // THEN
        assertNotNull("Oops, organization is null", org);
        assertEquals("Incorrect organization name", organizationName, org.getName());
    }

    @Test
    public void shouldReturnMemberByUsername() {
        // GIVEN
        String username = "joelsoderstrom";

        // WHEN
        Member member = new TrelloImpl(API_KEY, null).getMember(username);

        // THEN
        assertNotNull("Oops, member is null", member);
        assertNotNull("Avatar hash not set", member.getAvatarHash());
        assertEquals("Incorrect full name", "Joel Söderström", member.getFullName());
        assertNotNull("ID not set", member.getId());
        assertTrue("Invalid count of boards", member.getIdBoards().size() > 0);
        assertTrue("Invalid count of organizations", member.getIdOrganizations().size() > 0);
        assertEquals("Incorrect initials", "JS", member.getInitials());
        assertNotNull("Status not set", member.getStatus());
        assertEquals("Incorrect URL", "https://trello.com/joelsoderstrom", member.getUrl());
        assertEquals("Incorrect username", username, member.getUsername());
    }

    @Test
    public void shouldReturnMemberById() {
        // GIVEN
        String memberId = "4e918355e52581aa44eb0754";

        // WHEN
        Member member = new TrelloImpl(API_KEY, null).getMember(memberId);

        // THEN
        assertNotNull("Oops, member is null", member);
        assertEquals("Incorrect username", "joelsoderstrom", member.getUsername());
    }

    @Test
    public void shouldReturnBoardsByOrganization() {
        // GIVEN
        String organizationName = "teste21917343";
        String trelloDevBoardId = "564c5e78a5ccfadecdc8c053";

        // WHEN
        List<Board> boards = new TrelloImpl(API_KEY, null).getBoardsByOrganization(organizationName);

        // THEN
        assertTrue("Organization should have at least one board", boards.size() > 0);
        assertTrue("Organization FogCreek should have Trello Development board", hasBoardWithId(boards, trelloDevBoardId));
    }

    @Test
    public void shouldReturnCard() {
        // GIVEN
        String cardId = "564c6a6892b15203af243f3d";

        // WHEN
        Card card = new TrelloImpl(API_KEY, null).getCard(cardId);

        // THEN
        assertNotNull("Oops, card is null", card);
        assertEquals("Card id should be equal", cardId, card.getId());

		// if(!card.getAttachments().isEmpty()) {
        // 	assertNotNull("Attachment should be set", card.getAttachments().get(0).get_id());
        // }
    }

    @Test
    public void shouldReturnList() {
        // GIVEN
        String listId = "4e7b86d7ce194786721560b8";

        // WHEN
        org.trello4j.model.List list = new TrelloImpl(API_KEY, null).getList(listId);

        // THEN
        assertNotNull("Oops, list is null", list);
        assertEquals("Card id should be equal", listId, list.getId());
    }

    @Test
    public void shouldReturnNotification() {
        // GIVEN
        String notificationId = "564c927b245949f8b4216a07";

        // WHEN
        Notification notification = new TrelloImpl(API_KEY, API_TOKEN).getNotification(notificationId);

        // THEN
        assertNotNull("Oops, notification is null", notification);
        assertEquals("Notification id should be equal", notificationId, notification.getId());
    }

    @Test
    public void shouldReturnBoardsByMember() {
        // GIVEN
        String userId = "564afe97177cd7ad2fa084c0";

        // WHEN
        List<Board> boards = new TrelloImpl(API_KEY, API_TOKEN).getBoardsByMember(userId);

        // THEN
        assertNotNull("Oops, board list is null", boards);
        assertTrue("Member should have at least one board", boards.size() > 0);
    }

    @Test
    public void shouldReturnActionsByOrganization() {
        // GIVEN
        String organizationName = "teste21917343";

        // WHEN
        List<Action> actions = new TrelloImpl(API_KEY, API_TOKEN).getActionsByOrganization(organizationName);

        // THEN
        assertNotNull("Oops, action list is null", actions);
        assertTrue("Organization should have at least one action", actions.size() > 0);
    }

    @Test
    public void shouldReturnChecklist() {
        // GIVEN
        String checklistId = "4f92b89ea73738db6cdd4ed7";

        // WHEN
        Checklist checklist = new TrelloImpl(API_KEY, API_TOKEN).getChecklist(checklistId);

        // THEN
        assertNotNull("Oops, checklist list is null", checklist);
        assertEquals("Checklist id should match", checklistId, checklist.getId());
    }

    @Test
    public void shouldReturnTypeById() {
        // GIVEN
        String typeId = "4eb3f3f1e679eb839b4c594b";

        // WHEN
        Type type = new TrelloImpl(API_KEY, null).getType(typeId);

        // THEN
        assertNotNull("Oops, type is null", type);
        assertEquals("Incorrect id", typeId, type.getId());
        assertEquals("Incorrect trello type", TrelloType.ORGANIZATION, type.getType());
    }

    @Test
    public void shouldReturnTypeByName() {
        // GIVEN
        String typeName = "fogcreek";

        // WHEN
        Type type = new TrelloImpl(API_KEY, null).getType(typeName);

        // THEN
        assertNotNull("Oops, type is null", type);
        assertEquals("Incorrect trello type", TrelloType.ORGANIZATION, type.getType());
    }

    /**
     * Checks for board with id.
     *
     * @param boards the boards
     * @param id the id
     * @return true, if successful
     */
    private boolean hasBoardWithId(List<Board> boards, String id) {
        boolean res = false;
        for (Board board : boards) {
            if (board.getId().equals(id)) {
                res = true;
                break;
            }
        }
        return res;
    }
}
