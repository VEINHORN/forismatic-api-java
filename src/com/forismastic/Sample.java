package com.forismastic;

import com.forismastic.Forismatic.Quote;

public class Sample {
	public static void main(String[] args) {
		Quote quote = null;
		for(int i = 0; i < 5; i++) {
			if(i % 2 == 0)
				quote = new Forismatic(Forismatic.RUSSIAN).getQuote();
			else {
				quote = new Forismatic(Forismatic.ENGLISH).getQuote();
			}
			System.out.println(quote.getQuoteText());
			System.out.println(quote.getQuoteAuthor());
			try {
				Thread.sleep(3000);
			} catch(InterruptedException e) {
						
			}
		}
	}
}
