package com.veinhorn.forismatic.example;

import com.veinhorn.forismatic.api.Forismatic;
import com.veinhorn.forismatic.api.Quote;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Quote quote = new Forismatic(Forismatic.ENGLISH).getQuote();
        System.out.println(String.format("\"%s\"", quote.getQuoteText()));
        System.out.println(String.format("\n\t © %s", getAuthor(quote)));
    }

    private static String getAuthor(Quote quote) {
        return quote.getQuoteAuthor().orElse("Unknown author");
    }
}
