package ru.vlasova.exchangeRates.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Алина on 17.10.2016.
 */
public class HTMLParserTest {

    @Test
    public void testGetExchangeByName() {
        HTMLParser parser = new HTMLParser("11.08.2015");
        assertEquals(Float.valueOf(64.4977f), parser.getExchangeByName(CurrenciesNames.USD));
        assertEquals(Float.valueOf(1.0f), parser.getExchangeByName(CurrenciesNames.RUB));
    }
}