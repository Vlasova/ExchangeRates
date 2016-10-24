package ru.vlasova.exchangeRates.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Алина on 17.10.2016.
 */
public class CurrencyTest {

    Currency currency1 = new Currency();
    Currency currency2 = new Currency(CurrenciesNames.EUR, "01.01.2016");
    Currency currency3 = new Currency(CurrenciesNames.USD, new Day().getTodayDate());

    @Test
    public void testGetExchange() {
        assertEquals(Float.valueOf(1.0f), Float.valueOf(currency1.getExchange()));
        assertEquals(Float.valueOf(79.6395f), Float.valueOf(currency2.getExchange()));
    }

    @Test
    public void testGetName() {
        assertEquals(CurrenciesNames.RUB, currency1.getName());
        assertEquals(CurrenciesNames.EUR, currency2.getName());
    }

    @Test
    public void testGetRussianName() {
        assertEquals("Российский рубль", currency1.getRussianName());
        assertEquals("Евро", currency2.getRussianName());
    }

    @Test
    public void testIsHigher() {
        assertEquals(false, currency3.isTodayHigher());
    }
}