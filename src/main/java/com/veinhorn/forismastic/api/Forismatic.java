package com.veinhorn.forismastic.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.Random;

public class Forismatic {
	private final static String BASE_URL = "https://api.forismatic.com/api/1.0/";
	private final static String API_METHOD_TITLE = "method";
	private final static String API_FORMAT_TITLE = "format";
	private final static String API_KEY_TITLE = "key";
	private final static String API_LANG_TITLE = "lang";
	private final static String API_METHOD = "getQuote";
	private final static String API_XML = "xml";
	private final static String XML_QUOTE_TEXT_PATH = "/forismatic/quote/quoteText";
	private final static String XML_QUOTE_TEXT_AUTHOR_PATH = "/forismatic/quote/quoteAuthor";
	public final static String RUSSIAN = "ru";
	public final static String ENGLISH = "en";

    private final static int MIN = 1;
    private final static int MAX = 999999;

	private String language;
	
	public Forismatic() {
		this.language = RUSSIAN;
	}
	
	public Forismatic(String language) {
		this.language = language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public Quote getQuote() throws IOException {
		String quoteAsXml = retrieveQuoteAsXML();
		return parseXML(quoteAsXml);
	}
	
	private Quote parseXML(String xmlString) {
		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath= xPathFactory.newXPath();
		InputSource source1 = new InputSource(new StringReader(xmlString)), source2 = new InputSource(new StringReader(xmlString));
		String text = null, author = null;
		try {
			text = xPath.evaluate(XML_QUOTE_TEXT_PATH, source1);
			author = xPath.evaluate(XML_QUOTE_TEXT_AUTHOR_PATH, source2);
		} catch(XPathException e) {
			e.printStackTrace();
		}
		return new Quote(text, author);
	}
	
	private String retrieveQuoteAsXML() throws IOException {
		String urlParametersString = API_METHOD_TITLE + "=" + API_METHOD +
									"&" + API_FORMAT_TITLE + "=" + API_XML +
									"&" + API_KEY_TITLE + "=" + getRandom().toString() +
									"&" + API_LANG_TITLE + "=" + language;
		String url = String.format("%s?%s", BASE_URL, urlParametersString);

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url(url)
				.build();

		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	private Integer getRandom() {
        return new Random().nextInt((MAX - MIN) + 1) + MIN;
	}
}