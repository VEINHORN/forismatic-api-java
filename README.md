#forismatic-api-java

[![ScreenShot](http://i.imgur.com/ueC0i4f.png)](http://forismatic.com/ru/)

##### Forismatic.com API client for Java. It's a simple way to get most inspirational quotes. With this library you can get best quotes of famous people.
To get a Quote object you need to call getQuote() method:
```java
Quote quote = new Forismatic().getQuote();
```
API supports English & Russian languages. The default language for quotes is Russian. You can change it by putting constant to constructor:
```java
Quote quote = new Forismatic(Forismatic.ENGLISH).getQuote();
```
or call setLanguage() method:
```java
Forismatic forismatic = new Forismatic();
forismatic.setLanguage();
Quote quote = forismatic.getQuote();
```
To get quote text and author, call getQuoteText() and getQuoteAuthor() methods:
```java
String quoteText = quote.getQuoteText();
String quoteAuthor = quote.getQuoteAuthor();
```
To use this library in your project, just add Forismatic.java class or jar file.
[Download forismatic-api-java jar](https://dl.dropboxusercontent.com/s/uvr6ns972q8zg6f/forismatic-api-java.jar?dl=1&token_hash=AAEx4n8XUoC_SSSMuxHARTMQ_JPHK17Rceb2ihOdtziEYQ)  

###For Android developers

To use this library you need the minimum api level 8. Also add the following to your Manifest:
```xml
<uses-permission android:name="android.permission.INTERNET"/>
```
###Applications using forismatic-api-java
[![ScreenShot](http://i.imgur.com/AguXNK1.png?2)](https://github.com/VEINHORN/forismatic-api-java)[![ScreenShot](http://i.imgur.com/qPqU49b.png?1)](https://github.com/VEINHORN/forismatic-api-java)
