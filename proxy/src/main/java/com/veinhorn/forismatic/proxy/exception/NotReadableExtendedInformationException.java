package com.veinhorn.forismatic.proxy.exception;

public class NotReadableExtendedInformationException extends RuntimeException {
    public NotReadableExtendedInformationException(String propertyName) {
        super(String.format("Cannot read %s from JWT extended information", propertyName));
    }
}
