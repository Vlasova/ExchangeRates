package ru.vlasova.exchangeRates.core;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Алина on 24.10.2016.
 */
public class DayTest {

    Day day = new Day();

    @Test
    public void testGetTodayDate() {
        assertEquals("24.10.2016", day.getTodayDate());
    }

    @Test
    public void testGetYesterdayDate() {
        assertEquals("23.10.2016", day.getYesterdayDate());
    }

    @Test
    public void testAddDay() {
        assertEquals("25.10.2016", day.addDay("24.10.2016"));
    }

}