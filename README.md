#forismatic-api-java

[![ScreenShot](http://i.imgur.com/ueC0i4f.png)](http://forismatic.com/ru/)

#####The Java library for the forismatic.com API. It's the simple way to get the most inspirational quotes. Using this library you can get the best quotes of the famous people in random order.
To get the Quote object you need to call the getQuote method.
```java
Quote quote = new Forismatic().getQuote();
```
The default language for quotes is English. To change it you can put the constant to constructor.
```java
Quote quote = new Forismatic(Forismatic.RUSSIAN).getQuote();
```
or call setLanguage method.
```java
Forismatic forismatic = new Forismatic();
forismatic.setLanguage();
Quote quote = forismatic.getQuote();
```
There are 2 supported languages for quotes. English & Russian. To get the quote text and the author of a quote call getQuoteText and getQuoteAuthor methods.
```java
String quoteText = quote.getQuoteText();
String quoteAuthor = quote.getQuoteAuthor();
```
To use this code in your project just add Forismatic.java class or jar file to your project.
[Download forismatic-api-java jar](https://dl.dropboxusercontent.com/s/uvr6ns972q8zg6f/forismatic-api-java.jar?dl=1&token_hash=AAEx4n8XUoC_SSSMuxHARTMQ_JPHK17Rceb2ihOdtziEYQ)  

###For Android developers.

To use this library you need the minimum api level 8. And add to your Manifest.
```xml
<uses-permission android:name="android.permission.INTERNET"/>
```
