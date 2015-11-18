/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trello4j.http.rest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import org.trello4j.exception.TrelloException;

/**
 *
 * @author Emanuel Batista da Silva Filho - https://github.com/emanuelbatista
 */
public class TrelloHttpRest {

    private static final String METHOD_DELETE = "DELETE";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String GZIP_ENCODING = "gzip";

    public static InputStream doGet(String url) {
        return doRequest(url, METHOD_GET);
    }

    public static InputStream doPut(String url) {
        return doRequest(url, METHOD_PUT);
    }

    public static InputStream doPost(String url, Map<String, String> map) {
        return doRequest(url, METHOD_POST, map);
    }

    public static InputStream doDelete(String url) {
        return doRequest(url, METHOD_DELETE);
    }

    public static InputStream doRequest(String url, String requestMethod) {
        return doRequest(url, requestMethod, null);
    }

    /**
     * Execute a POST request with URL-encoded key-value parameter pairs.
     *
     * @param url Trello API URL.
     * @param map Key-value map.
     * @return the response input stream.
     */
    private static InputStream doRequest(String url, String requestMethod, Map<String, String> map) {
        try {
            HttpsURLConnection conn = (HttpsURLConnection) new URL(url)
                    .openConnection();
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setDoOutput(requestMethod.equals(METHOD_POST) || requestMethod.equals(METHOD_PUT));
            conn.setRequestMethod(requestMethod);

            if (map != null && !map.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (String key : map.keySet()) {
                    sb.append(sb.length() > 0 ? "&" : "")
                            .append(key)
                            .append("=")
                            .append(URLEncoder.encode(map.get(key), "UTF-8"));
                }
                conn.getOutputStream().write(sb.toString().getBytes());
                conn.getOutputStream().close();
            }

            if (conn.getResponseCode() > 399) {
                return null;
            } else {
                return getWrappedInputStream(
                        conn.getInputStream(), GZIP_ENCODING.equalsIgnoreCase(conn.getContentEncoding())
                );
            }
        } catch (IOException e) {
            throw new TrelloException(e.getMessage());
        }
    }


    private static InputStream getWrappedInputStream(InputStream is, boolean gzip)
            throws IOException {
        /*
         * TODO: What about this? ---------------------- "Java clients which use
         * java.util.zip.GZIPInputStream() and wrap it with a
         * java.io.BufferedReader() to read streaming API data will encounter
         * buffering on low volume streams, since GZIPInputStream's available()
         * method is not suitable for streaming purposes. To fix this, create a
         * subclass of GZIPInputStream() which overrides the available()
         * method."
         * 
         * https://dev.twitter.com/docs/streaming-api/concepts#gzip-compression
         */
        if (gzip) {
            return new BufferedInputStream(new GZIPInputStream(is));
        } else {
            return new BufferedInputStream(is);
        }
    }
}
