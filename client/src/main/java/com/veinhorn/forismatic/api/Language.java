package com.veinhorn.forismatic.api;

public enum Language {
    ENGLISH("en"),
    RUSSIAN("ru");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
