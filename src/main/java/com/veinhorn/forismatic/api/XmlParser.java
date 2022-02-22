package com.veinhorn.forismatic.api;

import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.Optional;

class XmlParser {
    private final static String XML_QUOTE_TEXT_PATH = "/forismatic/quote/quoteText";
    private final static String XML_QUOTE_TEXT_AUTHOR_PATH = "/forismatic/quote/quoteAuthor";

    private String quoteAsXml;
    private XPath xPath;

    public XmlParser(String quoteAsXml) {
        this.quoteAsXml = quoteAsXml;
        this.xPath = XPathFactory.newInstance().newXPath();
    }

    public Quote parse() {
        Quote quote = null;

        try {
            quote = new Quote(parseQuoteText(), parseQuoteAuthor());
        } catch(XPathException e) {
            System.out.println(String.format("Failed parse quote because of: %s", e.getMessage()));
            e.printStackTrace();
        }

        return quote;
    }

    private String parseQuoteText() throws XPathExpressionException {
        return xPath.evaluate(XML_QUOTE_TEXT_PATH, new InputSource(new StringReader(quoteAsXml)));
    }

    private Optional<String> parseQuoteAuthor() throws XPathExpressionException {
        String quoteAuthor = xPath.evaluate(XML_QUOTE_TEXT_AUTHOR_PATH, new InputSource(new StringReader(quoteAsXml)));
        return quoteAuthor != null && !quoteAuthor.isEmpty() ? Optional.of(quoteAuthor) : Optional.empty();
    }
}
