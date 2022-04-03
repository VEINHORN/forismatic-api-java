package com.veinhorn.forismatic.proxy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateQuoteException extends Exception {
    public DuplicateQuoteException() {
        super("Quote with such text already exists");
    }
}
