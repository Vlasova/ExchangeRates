package ru.vlasova.exchangeRates.core;
// todo тесты обычно находятся в src/test/java....

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Алина on 17.10.2016.
 */
public class CurrencyTest {

    Currency currency1 = new Currency(CurrenciesNames.EUR, "01.01.2016");
    Currency currency2 = new Currency(CurrenciesNames.USD, new Day().getTodayDate());

    @Test
    public void testGetExchange() {
        assertEquals(79.6395f, currency1.getExchange(), 0.00001);
        assertEquals(63.4161f, currency2.getExchange(), 0.00001);
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
    // todo а если наступит день, когда евро будет меньше, чем 01.01.2016?
    // наверное, стоит взять две даты из прошлого с фиксированным курсом и сравнивать их.
    public void testIsHigher() {
        assertEquals(true, currency1.isTodayHigher());
    }
}