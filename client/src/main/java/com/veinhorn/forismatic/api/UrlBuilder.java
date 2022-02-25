package com.veinhorn.forismatic.api;

public class UrlBuilder {
    private final static String BASE_URL = "https://api.forismatic.com/api/1.0/";
    private final static String API_METHOD_TITLE = "method";
    private final static String API_FORMAT_TITLE = "format";
    private final static String API_KEY_TITLE = "key";
    private final static String API_LANG_TITLE = "lang";
    private final static String API_METHOD = "getQuote";
    private final static String API_XML = "xml";

    private final Language language;
    private final String key;

    public UrlBuilder(Language language, String key) {
        this.language = language;
        this.key = key;
    }

    public String build() {
        return String.format("%s?%s", BASE_URL, queryParams());
    }

    private String queryParams() {
        return API_METHOD_TITLE + "=" + API_METHOD +
                "&" + API_FORMAT_TITLE + "=" + API_XML +
                "&" + API_KEY_TITLE + "=" + key +
                "&" + API_LANG_TITLE + "=" + language.value();
    }
}
