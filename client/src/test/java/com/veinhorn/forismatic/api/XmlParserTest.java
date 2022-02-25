package com.veinhorn.forismatic.api;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
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
        assertEquals(Optional.of("http://forismatic.com/ru/024ea407a2/"), quote.getQuoteLink());
    }

    @Test
    public void testParsing2() throws IOException {
        Quote quote = new XmlParser(getResourceAsString("/response_example_2.xml")).parse();

        assertNotNull(quote);
        assertEquals("Несведущий человек действует смелей.", quote.getQuoteText());
        assertEquals(Optional.empty(), quote.getQuoteAuthor());
        assertEquals(Optional.of("http://forismatic.com/ru/239cb0eb50/"), quote.getQuoteLink());
    }

    @Test
    public void testParsing3() throws IOException {
        Quote quote = new XmlParser(getResourceAsString("/response_example_3.xml")).parse();

        assertNotNull(quote);
        assertEquals("Те, которые смирят свою мысль, блуждающую вдалеке, бредущую в одиночку, бестелесную, скрытую в сердце, освободятся.", quote.getQuoteText());
        assertEquals(Optional.of("Франц Кафка"), quote.getQuoteAuthor());
        assertEquals(Optional.empty(), quote.getQuoteLink());
    }

    private String getResourceAsString(String resourcePath) throws IOException {
        return IOUtils.toString(getClass().getResourceAsStream(resourcePath), StandardCharsets.UTF_8);
    }
}