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
        assertEquals(62.4499f, exchangeRates.getTodayExchange(CurrenciesNames.USD), 0.00001);
        assertEquals(47.1605f, exchangeRates.getTodayExchange(CurrenciesNames.CAD), 0.00001);
    }

    @Test
    public void testGetAllTodayExchange() {
        assertEquals(62.2349f, exchangeRates.getAllTodayExchanges().get(CurrenciesNames.USD.ordinal()-1).getExchange(), 0.00001);
        assertEquals(46.6354f, exchangeRates.getAllTodayExchanges().get(CurrenciesNames.CAD.ordinal()-1).getExchange(), 0.00001);
    }

    @Test
    public void testGetExchangeByDate() {
        assertEquals(53.3701f, exchangeRates.getExchangeByDate(CurrenciesNames.AUD, "01.01.2016"), 0.00001);
        assertEquals(72.9299f, exchangeRates.getExchangeByDate(CurrenciesNames.USD, "01.01.2016"), 0.00001);
    }

    @Test
    public void testGetAllExchangeByDate() {
        assertEquals(53.3701f, exchangeRates.getAllExchangesByDate("01.01.2016").get(CurrenciesNames.AUD.ordinal()-1).getExchange(), 0.00001);
        assertEquals(72.9299f, exchangeRates.getAllExchangesByDate("01.01.2016").get(CurrenciesNames.USD.ordinal()-1).getExchange(), 0.00001);
    }

    @Test
     public void testConvert() {
        assertEquals(1.09f, exchangeRates. convert(CurrenciesNames.EUR, CurrenciesNames.USD, 1), 0.00001);
        assertEquals(1130.8f, exchangeRates.convert(CurrenciesNames.EUR, CurrenciesNames.JPY, 10), 0.00001);
    }

    @Test
    public  void testGetStatistics() {
        assertEquals(64.8306f, exchangeRates.getStatistics(CurrenciesNames.USD, "01.09.2016", "10.09.2016").get(6).getExchange(), 0.00001);
        assertEquals(64.1617f, exchangeRates.getStatistics(CurrenciesNames.USD, "01.09.2016", "10.09.2016").get(9).getExchange(), 0.00001);
    }
}