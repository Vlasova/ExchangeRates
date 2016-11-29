package ru.vlasova.exchangeRates.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Алина on 24.10.2016.
 */
public class DayTest {

    @Test
    public void testGetDate() {
        assertEquals("01.08.2016", Day.getDate("01.08.2016"));
        assertEquals("01.01.2016", Day.getDate("01.01.2016"));

    }

    @Test
    public void testGetPastDate() {
        assertEquals("31.07.2016", Day.getPastDate("01.08.2016"));
        assertEquals("31.12.2015", Day.getPastDate("01.01.2016"));
    }

    @Test
    public void testGetNextDateDay() {
        assertEquals("02.08.2016", Day.getNextDate("01.08.2016"));
        assertEquals("02.01.2016", Day.getNextDate("01.01.2016"));
    }
}