package com.veinhorn.forismatic.proxy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class QuoteNotFoundException extends ResponseStatusException {
    public QuoteNotFoundException() {
        super(HttpStatus.NOT_FOUND, "quote not found");
    }
}
