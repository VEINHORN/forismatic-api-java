package com.veinhorn.forismatic.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Forismatic {
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
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url(new UrlBuilder(language, new Randomizer().getRandom()).build())
				.build();

		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
}