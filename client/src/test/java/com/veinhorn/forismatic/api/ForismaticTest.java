package com.veinhorn.forismatic.api;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ForismaticTest {
    @Test
    public void testApi() throws IOException {
        Quote quote = new Forismatic(Language.RUSSIAN).getQuote();

        assertNotNull(quote);
        assertNotNull(quote.getQuoteText());
    }
}
