package com.veinhorn.forismatic.api;

import java.util.Optional;

public class Quote {
    private final String quoteText;
    private final Optional<String> quoteAuthor;
    private final Optional<String> quoteLink;


    public Quote(String quoteText, Optional<String> quoteAuthor, Optional<String> quoteLink) {
        this.quoteText = quoteText;
        this.quoteAuthor = quoteAuthor;
        this.quoteLink = quoteLink;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public Optional<String> getQuoteAuthor() {
        return quoteAuthor;
    }

    public Optional<String> getQuoteLink() {
        return quoteLink;
    }
}
