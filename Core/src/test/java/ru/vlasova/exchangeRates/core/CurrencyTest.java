package ru.vlasova.exchangeRates.core;
// todo тесты обычно находятся в src/test/java....

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Алина on 17.10.2016.
 */
public class CurrencyTest {

    Currency currency1 = new Currency(CurrenciesNames.EUR, "01.01.2016");
    Currency currency2 = new Currency(CurrenciesNames.USD, Day.getPastDate("01.01.2016"));

    @Test
    public void testGetExchange() {
        assertEquals(79.6395, currency1.getExchange(), 0.00001);
        assertEquals(72.8827, currency2.getExchange(), 0.00001);
    }

    @Test
    public void testGetName() {
        assertEquals(CurrenciesNames.EUR, currency1.getName());
        assertEquals(CurrenciesNames.USD, currency2.getName());
    }

    @Test
    public void testGetRussianName() {
        assertEquals("Евро", currency1.getRussianName());
        assertEquals("Доллар США", currency2.getRussianName());
    }

    @Test
    public void testIsHigher() {
        assertEquals(false, currency1.isHigher());
        assertEquals(true, currency2.isHigher());
    }
}