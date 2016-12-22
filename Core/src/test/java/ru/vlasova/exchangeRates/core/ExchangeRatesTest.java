package ru.vlasova.exchangeRates.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Алина on 18.10.2016.
 */
public class ExchangeRatesTest {

    ExchangeRates exchangeRates = new ExchangeRates();

    @Test
    public void testGetExchange() {
        assertEquals(53.3701, exchangeRates.getExchange(CurrenciesNames.AUD, "01.01.2016"), 0.00001);
        assertEquals(72.9299, exchangeRates.getExchange(CurrenciesNames.USD, "01.01.2016"), 0.00001);
    }

    @Test
    public void testGetAllExchanges() {
        assertEquals(53.3701, exchangeRates.getAllExchanges("01.01.2016").get(CurrenciesNames.AUD.ordinal()).getExchange(), 0.00001);
        assertEquals(72.9299, exchangeRates.getAllExchanges("01.01.2016").get(CurrenciesNames.USD.ordinal()).getExchange(), 0.00001);
    }

    @Test
     public void testConvert() {
        assertEquals(1.09, exchangeRates. convert(CurrenciesNames.EUR, CurrenciesNames.USD, 1), 0.00001);
        assertEquals(1130.8, exchangeRates.convert(CurrenciesNames.EUR, CurrenciesNames.JPY, 10), 0.00001);
    }
}