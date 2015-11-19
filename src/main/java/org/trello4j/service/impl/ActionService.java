/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trello4j.service.impl;

import com.google.gson.reflect.TypeToken;
import org.trello4j.TrelloObjectFactoryImpl;
import org.trello4j.TrelloURL;
import org.trello4j.http.rest.TrelloHttpRest;
import org.trello4j.model.Action;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.List;
import org.trello4j.model.Member;
import org.trello4j.model.Organization;
import org.trello4j.util.TrelloIdValid;

/**
 *
 * @author Emanuel Batista da Silva Filho - https://github.com/emanuelbatista
 */
class ActionService implements org.trello4j.service.ActionService {

    private final String APIKEY;
    private final String TOKEN;
    private final TrelloObjectFactoryImpl TRELLO_OBJECT_FACTORY;

    public ActionService(String apiKey, String token, TrelloObjectFactoryImpl trelloObjFactory) {
        this.APIKEY = apiKey;
        this.TOKEN = token;
        this.TRELLO_OBJECT_FACTORY = trelloObjFactory;
    }

    @Override
    public Action getAction(String actionId, String... filter) {
        TrelloIdValid.validateObjectId(actionId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.ACTION_URL, actionId)
                .token(TOKEN)
                .filter(filter)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<Action>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public Board getBoardByAction(String actionId, String... filter) {
        TrelloIdValid.validateObjectId(actionId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.ACTION_BOARD_URL, actionId)
                .token(TOKEN)
                .filter(filter)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<Board>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public Card getCardByAction(String actionId, String... filter) {
        TrelloIdValid.validateObjectId(actionId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.ACTION_CARD_URL, actionId)
                .token(TOKEN)
                .filter(filter)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<Card>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public Member getMemberByAction(String actionId, String... filter) {
        TrelloIdValid.validateObjectId(actionId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.ACTION_MEMBER_URL, actionId)
                .token(TOKEN)
                .filter(filter)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<Member>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public Member getMemberCreatorByAction(String actionId, String... filter) {
        TrelloIdValid.validateObjectId(actionId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.ACTION_MEMBERCREATOR_URL, actionId)
                .token(TOKEN)
                .filter(filter)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<Member>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public Organization getOrganizationByAction(String actionId, String... filter) {
        TrelloIdValid.validateObjectId(actionId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.ACTION_ORGANIZATION_URL, actionId)
                .token(TOKEN)
                .filter(filter)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<Organization>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public List getListByAction(String actionId, String... filter) {
        TrelloIdValid.validateObjectId(actionId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.ACTION_LIST_URL, actionId)
                .token(TOKEN)
                .filter(filter)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<List>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public void removeAction(String actionId) {
        TrelloIdValid.validateObjectId(actionId);

        final String url=TrelloURL.create(APIKEY, TrelloURL.ACTION_URL, actionId)
                .token(TOKEN)
                .build();
        TrelloHttpRest.doDelete(url);
    }

}
