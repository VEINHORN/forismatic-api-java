package com.veinhorn.forismatic.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Random;

public class Forismatic {
	private final static String BASE_URL = "https://api.forismatic.com/api/1.0/";
	private final static String API_METHOD_TITLE = "method";
	private final static String API_FORMAT_TITLE = "format";
	private final static String API_KEY_TITLE = "key";
	private final static String API_LANG_TITLE = "lang";
	private final static String API_METHOD = "getQuote";
	private final static String API_XML = "xml";

    private final static int MIN = 1;
    private final static int MAX = 999999;

	private Language language;
	
	public Forismatic() {
		this.language = Language.RUSSIAN;
	}
	
	public Forismatic(Language language) {
		this.language = language;
	}
	
	public void setLanguage(Language language) {
		this.language = language;
	}
	
	public Language getLanguage() {
		return language;
	}
	
	public Quote getQuote() throws IOException {
		return new XmlParser(retrieveQuoteAsXML()).parse();
	}
	

	
	private String retrieveQuoteAsXML() throws IOException {
		String urlParametersString = API_METHOD_TITLE + "=" + API_METHOD +
									"&" + API_FORMAT_TITLE + "=" + API_XML +
									"&" + API_KEY_TITLE + "=" + getRandom().toString() +
									"&" + API_LANG_TITLE + "=" + language.value();
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