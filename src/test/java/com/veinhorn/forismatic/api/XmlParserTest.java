package com.veinhorn.forismatic.api;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XmlParserTest {
    @Test
    public void testParsing1() throws IOException {
        Quote quote = new XmlParser(getResourceAsString("/response_example_1.xml")).parse();

        assertNotNull(quote);
        assertEquals("Разум может подсказать, чего следует избегать, и только сердце говорит, что следует делать.", quote.getQuoteText());
        assertEquals(Optional.of("Жозеф Жубер"), quote.getQuoteAuthor());
    }

    @Test
    public void testParsing2() throws IOException {
        Quote quote = new XmlParser(getResourceAsString("/response_example_2.xml")).parse();

        assertNotNull(quote);
        assertEquals("Несведущий человек действует смелей.", quote.getQuoteText());
        assertEquals(Optional.empty(), quote.getQuoteAuthor());
    }

    private String getResourceAsString(String resourcePath) throws IOException {
        return IOUtils.toString(getClass().getResourceAsStream(resourcePath), StandardCharsets.UTF_8);
    }
}