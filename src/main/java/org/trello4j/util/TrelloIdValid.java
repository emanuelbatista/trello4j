/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trello4j.util;

import org.trello4j.exception.TrelloException;

/**
 *
 * @author Emanuel Batista da Silva Filho - https://github.com/emanuelbatista
 */
public class TrelloIdValid {

    public static void validateObjectId(String id) {
        if (!isObjectIdValid(id)) {
            throw new TrelloException("Invalid object id: " + id);
        }
    }

    /**
     * "Stolen" from: https://github.com/mongodb/mongo-java-driver
     *
     * @param s
     * @return true, if is object id valid
     */
    private static boolean isObjectIdValid(String s) {
        if (s == null) {
            return false;
        }

        final int len = s.length();
        if (len != 24) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                continue;
            }
            if (c >= 'a' && c <= 'f') {
                continue;
            }
            if (c >= 'A' && c <= 'F') {
                continue;
            }

            return false;
        }

        return true;
    }
}
