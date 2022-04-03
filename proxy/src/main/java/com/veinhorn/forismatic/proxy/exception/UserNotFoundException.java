package com.veinhorn.forismatic.proxy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User not found");
    }
}
