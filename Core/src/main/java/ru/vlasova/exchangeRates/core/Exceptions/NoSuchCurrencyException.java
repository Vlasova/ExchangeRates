package ru.vlasova.exchangeRates.core.Exceptions;

import java.util.NoSuchElementException;

/**
 * Created by Алина on 29.11.2016.
 */
public class NoSuchCurrencyException extends NoSuchElementException {

    public NoSuchCurrencyException(String message) {
        super(message);
    }
}
