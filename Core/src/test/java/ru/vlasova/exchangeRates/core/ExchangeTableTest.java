package ru.vlasova.exchangeRates.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Алина on 18.10.2016.
 */
public class ExchangeTableTest {

    ExchangeTable table = new ExchangeTable("01.01.2016");

    @Test
    public void testGetExchangeTable() {
        assertEquals(Float.valueOf(53.3701f), table.getExchangeTable().get(CurrenciesNames.AUD));
        assertEquals(Float.valueOf(72.9299f), table.getExchangeTable().get(CurrenciesNames.USD));
    }

    @Test
    public void testGetExchangeByName() {
        assertEquals(Float.valueOf(53.3701f), table.getExchangeByName(CurrenciesNames.AUD));
        assertEquals(Float.valueOf(72.9299f), table.getExchangeByName(CurrenciesNames.USD));
    }

    @Test
    public void testGetDate() {
        assertEquals("01.01.2016", table.getDate());
    }

}