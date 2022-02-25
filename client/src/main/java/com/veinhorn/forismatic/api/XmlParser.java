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
    private final static String XML_QUOTE_LINK_PATH = "/forismatic/quote/quoteLink";

    private String quoteAsXml;
    private XPath xPath;

    public XmlParser(String quoteAsXml) {
        this.quoteAsXml = quoteAsXml;
        this.xPath = XPathFactory.newInstance().newXPath();
    }

    public Quote parse() {
        Quote quote = null;

        try {
            quote = new Quote(parseQuoteText(), parseQuoteAuthor(), parseQuoteLink());
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
        String quoteAuthor = xPath.evaluate(XML_QUOTE_TEXT_AUTHOR_PATH, quoteAsInputSource());
        return quoteAuthor != null && !quoteAuthor.isEmpty() ? Optional.of(quoteAuthor) : Optional.empty();
    }

    private Optional<String> parseQuoteLink() throws XPathExpressionException {
        String quoteLink = xPath.evaluate(XML_QUOTE_LINK_PATH, quoteAsInputSource());
        return quoteLink != null && !quoteLink.isEmpty() ? Optional.of(quoteLink) : Optional.empty();
    }

    private InputSource quoteAsInputSource() {
        return new InputSource(new StringReader(quoteAsXml));
    }
}
