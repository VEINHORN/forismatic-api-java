package com.veinhorn.forismatic.example;

import com.veinhorn.forismatic.api.Forismatic;
import com.veinhorn.forismatic.api.Language;
import com.veinhorn.forismatic.api.Quote;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Quote quote = new Forismatic(Language.ENGLISH).getQuote();
        System.out.printf("\"%s\"%n", quote.getQuoteText());
        System.out.printf("\n\t © %s%n", getAuthor(quote));
    }

    private static String getAuthor(Quote quote) {
        return quote.getQuoteAuthor().orElse("Unknown author");
    }
}
