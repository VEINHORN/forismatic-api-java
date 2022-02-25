# forismatic-api-java

[![](https://jitpack.io/v/VEINHORN/forismatic-api-java.svg)](https://jitpack.io/#VEINHORN/forismatic-api-java)

[![ScreenShot](http://i.imgur.com/ueC0i4f.png)](http://forismatic.com/ru/)

It's an Java client for the forismatic.com API. You can use this library in your Java project to get access to the 
best quotes of famous people.

## Dependency

### Using JitPack

__Step 1.__ Add the JitPack repository to your build file:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

__Step 2.__ Add the dependency

```xml
<dependency>
    <groupId>com.github.VEINHORN.forismatic-api-java</groupId>
    <artifactId>forismatic-client</artifactId>
    <version>3a7c2c856e</version>
</dependency>
```

You can specify version from JitPack label above or use __master-SNAPSHOT__ to get latest.

## Usage

To get a Quote object you need to call getQuote() method:

```java
Quote quote = new Forismatic().getQuote();
```

API supports English & Russian languages. The default language for quotes is English. You can change it
by putting constant to constructor:

```java
Quote quote = new Forismatic(Language.ENGLISH).getQuote();
```

or call setLanguage() method:

```java
Forismatic forismatic = new Forismatic();
forismatic.setLanguage(Language.ENGLISH);
Quote quote = forismatic.getQuote();
```

To get quote text and author, call getQuoteText() and getQuoteAuthor() methods:

```java
String quoteText = quote.getQuoteText();
Optional<String> quoteAuthor = quote.getQuoteAuthor();
```

### For Android developers

To use this library you need the minimum api level 8. Also add the following to your Manifest:

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

### Applications using forismatic-api-java

[![ScreenShot](http://i.imgur.com/AguXNK1.png?2)](https://github.com/VEINHORN/Elite-Quotes)[![ScreenShot](http://i.imgur.com/qPqU49b.png?1)](https://play.google.com/store/apps/details?id=com.elitequotes)

### License

    Copyright 2014, 2022 Boris Korogvich

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
