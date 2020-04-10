package com.currencyexchange.backend.exceptions;

public class CurrencyNotFoundException extends RuntimeException {

    public CurrencyNotFoundException(Integer id) {
        super("Could not find currency " + id);
    }
}
