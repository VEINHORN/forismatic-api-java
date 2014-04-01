package com.forismastic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

public class Forismatic {
	private final static String BASE_URL = "http://api.forismatic.com/api/1.0/";
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
	private String language;
	
	public class Quote {
		private String quoteText;
		private String quoteAuthor;
		
		public Quote() {
			
		}
		
		public Quote(String quoteText, String quoteAuthor) {
			this.quoteText = quoteText;
			this.quoteAuthor = quoteAuthor;
		}
		
		public String getQuoteText() {
			return quoteText;
		}

		public void setQuoteText(String quoteText) {
			this.quoteText = quoteText;
		}

		public String getQuoteAuthor() {
			return quoteAuthor;
		}

		public void setQuoteAuthor(String quoteAuthor) {
			this.quoteAuthor = quoteAuthor;
		}
	}
	
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
	
	public Quote getQuote() {
		return parseXML(getXML());
	}
	
	private Quote parseXML(String xmlString) {
		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath= xPathFactory.newXPath();
		InputSource source1 = new InputSource(new StringReader(xmlString)), source2 = new InputSource(new StringReader(xmlString));
		String text = null, author = null;
		try {
			text = xPath.evaluate(XML_QUOTE_TEXT_PATH, source1);
			author = xPath.evaluate(XML_QUOTE_TEXT_AUTHOR_PATH, source2);
		} catch(XPathException exception) {
			exception.printStackTrace();
		}
		return new Quote(text, author);
	}
	
	private String getXML() {
		String xmlString = "";
		String urlParametersString = API_METHOD_TITLE + "=" + API_METHOD + "&" +
									 API_FORMAT_TITLE + "=" + API_XML + "&" + API_KEY_TITLE + "=" + getRandom().toString() + "&" +
									 API_LANG_TITLE + "=" + language;
		try {
			URL url = new URL(BASE_URL);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("charset", "utf-8");
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParametersString);
			writer.flush();
			xmlString = convertInputStreamToString(connection);
			writer.close();
		} catch(MalformedURLException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return xmlString;
	}

	private Integer getRandom() {
		int min = 1, max = 999999;
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}
	
	private String convertInputStreamToString(URLConnection connection) {
		String xmlString = "", line = "";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			while((line = reader.readLine()) != null) {
				xmlString += line;
			}
			reader.close();
		} catch(UnsupportedEncodingException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return xmlString;
	}
}
