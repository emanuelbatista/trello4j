package org.trello4j.service;

import org.trello4j.model.Type;

/**
 * The Interface Trello.
 */
public interface Trello extends OrganizationService, NotificationService,
        CardService, ListService, MemberService,
        ChecklistService, TokenService {

    /**
     * Gets the type.
     *
     * @param idOrName the id or name
     * @return the type
     */
    Type getType(String idOrName);

    public ActionService getActionService();
    
    public BoardService getBoardService();

}
