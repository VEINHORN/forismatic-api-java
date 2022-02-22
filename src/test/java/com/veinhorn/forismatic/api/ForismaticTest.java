package com.veinhorn.forismatic.api;

import com.veinhorn.forismastic.api.Forismatic;
import com.veinhorn.forismastic.api.Quote;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ForismaticTest {
    @Ignore
    @Test
    public void testForismaticApi() throws IOException {
        Quote quote;

        for(int i = 0; i < 5; i++) {
            if(i % 2 == 0)
                quote = new Forismatic(Forismatic.RUSSIAN).getQuote();
            else {
                quote = new Forismatic(Forismatic.ENGLISH).getQuote();
            }

            System.out.println(quote.getQuoteText());
            System.out.println(quote.getQuoteAuthor());

            try {
                Thread.sleep(3000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testApi() throws IOException {
        Quote quote = new Forismatic(Forismatic.RUSSIAN).getQuote();

        assertNotNull(quote);
    }
}
