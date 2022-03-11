package com.veinhorn.forismatic.proxy.exception;

public class DuplicateQuoteException extends Exception {
    public DuplicateQuoteException() {
        super("duplicated quote");
    }
}
