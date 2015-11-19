package org.trello4j.service;

import org.trello4j.model.Action;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.List;
import org.trello4j.model.Member;
import org.trello4j.model.Organization;

/**
 * Possui todos os serviços responsáveis pelo gerenciamento de manipulação de
 * ações do <b>Trello</b>
 *
 * @author Emanuel Batista
 * @version 1.0
 */
public interface ActionService {

    /**
     * Retorna uma ação do Trello a partir do id
     *
     * @param actionId o id da ação do trello
     * @param filter filtro de pesquisa da ação - <b>opcional</b>
     * @return {@link Action}
     */
    Action getAction(String actionId, String... filter);

    /**
     * Retorna um quadro a partir do id de uma ação do Trello
     *
     * @param actionId o id da ação do trello
     * @param filter filtro de pesquisa da ação - <b>opcional</b>
     * @return {@link Board}
     */
    Board getBoardByAction(String actionId, String... filter);

    /**
     * Retorna um card a partir do id de uma ação do Trello
     *
     * @param actionId
     * @param filter
     * @return {@link Card}
     */
    Card getCardByAction(String actionId, String... filter);

    /**
     * Retorna um menbro a partir do id de uma ação do Trello
     *
     * @param actionId
     * @param filter
     * @return {@link Member}
     */
    Member getMemberByAction(String actionId, String... filter);

    /**
     * Retorna o membro criador a partir do id de uma ação do Trello
     *
     * @param actionId
     * @param filter
     * @return {@link Member}
     */
    Member getMemberCreatorByAction(String actionId, String... filter);

    /**
     * Retorna a organização a partir do id de uma ação do Trello
     *
     * @param actionId
     * @param filter
     * @return {@link Organization}
     */
    Organization getOrganizationByAction(String actionId, String... filter);

    /**
     *  Retorna a lista a partir do id de uma ação do Trello
     * 
     * @param actionId
     * @param filter
     * @return {@link List}
     */
    List getListByAction(String actionId, String... filter);
    
    /**
     * Remove uma ação do Trell o partir do id do mesmo
     * 
     * @param actionId 
     */
    void removeAction(String actionId);
    
    

}
