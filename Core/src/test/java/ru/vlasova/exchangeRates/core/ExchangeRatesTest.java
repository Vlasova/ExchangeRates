package ru.vlasova.exchangeRates.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Алина on 18.10.2016.
 */
public class ExchangeRatesTest {

    ExchangeRates exchangeRates = new ExchangeRates();

    @Test
    public void testGetTodayExchange() {
        assertEquals(Float.valueOf(63.151f), exchangeRates.getTodayExchange(CurrenciesNames.USD));
        assertEquals(Float.valueOf(47.9288f), exchangeRates.getTodayExchange(CurrenciesNames.CAD));
    }

    @Test
    public void testGetAllTodayExchange() {
        assertEquals(Float.valueOf(63.151f), exchangeRates.getAllTodayExchanges().get(CurrenciesNames.USD));
        assertEquals(Float.valueOf(47.9288f), exchangeRates.getAllTodayExchanges().get(CurrenciesNames.CAD));
    }

    @Test
    public void testGetExchangeByDate() {
        assertEquals(Float.valueOf(53.3701f), exchangeRates.getExchangeByDate(CurrenciesNames.AUD, "01.01.2016"));
        assertEquals(Float.valueOf(72.9299f), exchangeRates.getExchangeByDate(CurrenciesNames.USD, "01.01.2016"));
    }

    @Test
    public void testGetAllExchangeByDate() {
        assertEquals(Float.valueOf(53.3701f), exchangeRates.getAllExchangesByDate("01.01.2016").get(CurrenciesNames.AUD));
        assertEquals(Float.valueOf(72.9299f), exchangeRates.getAllExchangesByDate("01.01.2016").get(CurrenciesNames.USD));
    }

    @Test
    public void testIsHigher() {
        assertEquals(false, exchangeRates.isHigher(CurrenciesNames.EUR));
        assertEquals(true, exchangeRates.isHigher(CurrenciesNames.AUD));
    }

    @Test
     public void testConbert() {
        assertEquals(Float.valueOf(1.1f), exchangeRates. convert(CurrenciesNames.EUR, CurrenciesNames.USD, 1));
        assertEquals(Float.valueOf(1144.26f), exchangeRates.convert(CurrenciesNames.EUR, CurrenciesNames.JPY, 10));
    }
}