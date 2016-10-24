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
        assertEquals(Float.valueOf(62.4499f), exchangeRates.getTodayExchange(CurrenciesNames.USD));
        assertEquals(Float.valueOf(47.1605f), exchangeRates.getTodayExchange(CurrenciesNames.CAD));
    }

    @Test
    public void testGetAllTodayExchange() {
        assertEquals(Float.valueOf(62.2349f), exchangeRates.getAllTodayExchanges().get(CurrenciesNames.USD.ordinal()).getExchange());
        assertEquals(Float.valueOf(46.6354f), exchangeRates.getAllTodayExchanges().get(CurrenciesNames.CAD.ordinal()).getExchange());
    }

    @Test
    public void testGetExchangeByDate() {
        assertEquals(Float.valueOf(53.3701f), exchangeRates.getExchangeByDate(CurrenciesNames.AUD, "01.01.2016"));
        assertEquals(Float.valueOf(72.9299f), exchangeRates.getExchangeByDate(CurrenciesNames.USD, "01.01.2016"));
    }

    @Test
    public void testGetAllExchangeByDate() {
        assertEquals(Float.valueOf(53.3701f), exchangeRates.getAllExchangesByDate("01.01.2016").get(CurrenciesNames.AUD.ordinal()).getExchange());
        assertEquals(Float.valueOf(72.9299f), exchangeRates.getAllExchangesByDate("01.01.2016").get(CurrenciesNames.USD.ordinal()).getExchange());
    }

    @Test
     public void testConvert() {
        assertEquals(Float.valueOf(1.09f), exchangeRates. convert(CurrenciesNames.EUR, CurrenciesNames.USD, 1));
        assertEquals(Float.valueOf(1130.8f), exchangeRates.convert(CurrenciesNames.EUR, CurrenciesNames.JPY, 10));
    }

    @Test
    public  void testGetStatistics() {
        assertEquals(Float.valueOf(64.8306f), exchangeRates.getStatistics(CurrenciesNames.USD, "01.09.2016", "10.09.2016").get(6).getExchange());
        assertEquals(Float.valueOf(64.1617f), exchangeRates.getStatistics(CurrenciesNames.USD, "01.09.2016", "10.09.2016").get(9).getExchange());
    }
}