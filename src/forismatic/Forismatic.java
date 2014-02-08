package forismatic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class Forismatic {
	private final static String BASE_URL = "http://api.forismatic.com/api/1.0/";
	private final static String API_METHOD_TITLE = "method";
	private final static String API_FORMAT_TITLE = "format";
	private final static String API_KEY_TITLE = "key";
	private final static String API_LANG_TITLE = "lang";
	private final static String API_METHOD = "getQuote";
	private final static String API_XML = "xml";
	private final static String API_JSON = "json";
	private final static String API_JSONP = "jsonp";
	private final static String API_HTML = "html";
	private final static String API_TEXT = "text";
	
	public final static String RUSSIAN = "ru";
	public final static String ENGLISH = "en";
	
	private String language;
	
	public Forismatic() {
		this.language = RUSSIAN;
	}
	
	public Forismatic(String language) {
		this.language = language;
	}
	
	public Quote getQuote() {
		String urlParametersString = API_METHOD_TITLE + "=" + API_METHOD + "&" +
									 API_FORMAT_TITLE + "=" + API_XML + "&" + API_KEY_TITLE + "=" + getRandom().toString() + "&" +
									 API_LANG_TITLE + "=" + language;
		URL url = null;
		URLConnection connection;
		OutputStreamWriter writer;
		BufferedReader reader;
		try {
			url = new URL(BASE_URL);
			connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("charset", "utf-8");
			writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParametersString);
			writer.flush();
			String line;
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			writer.close();
			reader.close();
		} catch(MalformedURLException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return new Quote();
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getLanguage() {
		return language;
	}
	
	private Integer getRandom() {
		int min = 1;
		int max = 999999;
		Random random = new Random();
		Integer integer = random.nextInt((max - min) + 1) + min;
		return integer;
	}
}
