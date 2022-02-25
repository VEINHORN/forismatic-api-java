package com.veinhorn.forismatic.api;

import org.junit.Test;

import static org.junit.Assert.*;

public class UrlBuilderTest {
    @Test
    public void testUrlCreation() {
        String key = new Randomizer().getRandom();
        String actual = new UrlBuilder(Language.ENGLISH, key).build();
        String expected = String.format("https://api.forismatic.com/api/1.0/?method=getQuote&format=xml&key=%s&lang=en", key);
        assertEquals(expected, actual);
    }
}