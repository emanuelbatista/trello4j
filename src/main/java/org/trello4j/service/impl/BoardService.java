/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trello4j.service.impl;

import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.trello4j.TrelloObjectFactoryImpl;
import org.trello4j.TrelloURL;
import org.trello4j.http.rest.TrelloHttpRest;
import org.trello4j.model.Action;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Checklist;
import org.trello4j.model.Member;
import org.trello4j.model.Organization;
import org.trello4j.util.TrelloIdValid;

/**
 *
 * @author Emanuel Batista da Silva Filho - https://github.com/emanuelbatista
 */
class BoardService implements org.trello4j.service.BoardService {

    private final String APIKEY;
    private final String TOKEN;
    private final TrelloObjectFactoryImpl TRELLO_OBJECT_FACTORY;

    public BoardService(String APIKEY, String TOKEN, TrelloObjectFactoryImpl TRELLO_OBJECT_FACTORY) {
        this.APIKEY = APIKEY;
        this.TOKEN = TOKEN;
        this.TRELLO_OBJECT_FACTORY = TRELLO_OBJECT_FACTORY;
    }

    @Override
    public Board getBoard(String boardId) {
        TrelloIdValid.validateObjectId(boardId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.BOARD_URL, boardId)
                .token(TOKEN)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<Board>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public List<Action> getActionsByBoard(String boardId, String... filter) {
        TrelloIdValid.validateObjectId(boardId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.BOARD_ACTIONS_URL, boardId)
                .filter(filter)
                .token(TOKEN)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<List<Action>>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public Organization getOrganizationByBoard(String boardId, String... filter) {
        TrelloIdValid.validateObjectId(boardId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.BOARD_ORGANIZAION_URL, boardId)
                .filter(filter)
                .token(TOKEN)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<Organization>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public List<Member> getMembersInvitedByBoard(String boardId, String... filter) {
        TrelloIdValid.validateObjectId(boardId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.BOARD_ACTIONS_URL, boardId)
                .filter(filter)
                .token(TOKEN)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<List<Member>>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public List<Member> getMembersByBoard(String boardId, String... filter) {
        TrelloIdValid.validateObjectId(boardId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.BOARD_MEMBERS_URL, boardId)
                .filter(filter)
                .token(TOKEN)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<List<Member>>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public List<org.trello4j.model.List> getListByBoard(String boardId, String... filter) {
        TrelloIdValid.validateObjectId(boardId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.BOARD_LISTS_URL, boardId)
                .filter(filter)
                .token(TOKEN)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<List<org.trello4j.model.List>>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public List<Checklist> getChecklistByBoard(String boardId) {
        TrelloIdValid.validateObjectId(boardId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.BOARD_CHECKLISTS_URL, boardId)
                .token(TOKEN)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<List<Checklist>>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public List<Card> getCardsByBoard(String boardId, String... filter) {
        TrelloIdValid.validateObjectId(boardId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.BOARD_CARDS_URL, boardId)
                .filter(filter)
                .token(TOKEN)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<List<Card>>() {
        }, TrelloHttpRest.doGet(url));
    }

    @Override
    public Board.Prefs getPrefsByBoard(String boardId) {
        TrelloIdValid.validateObjectId(boardId);

        final String url = TrelloURL
                .create(APIKEY, TrelloURL.BOARD_LISTS_URL, boardId)
                .token(TOKEN)
                .build();

        return TRELLO_OBJECT_FACTORY.createObject(new TypeToken<Board.Prefs>() {
        }, TrelloHttpRest.doGet(url));
    }

}
