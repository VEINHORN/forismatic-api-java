package com.veinhorn.forismatic.api;

import java.util.Optional;

public class Quote {
    private final String quoteText;
    private final Optional<String> quoteAuthor;


    public Quote(String quoteText, Optional<String> quoteAuthor) {
        this.quoteText = quoteText;
        this.quoteAuthor = quoteAuthor;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public Optional<String> getQuoteAuthor() {
        return quoteAuthor;
    }
}
