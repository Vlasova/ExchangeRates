package ru.vlasova.exchangeRates.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Алина on 24.10.2016.
 */
public class Day {
    private SimpleDateFormat sdf;
    private Calendar calendar;

    public Day() {
        sdf = new SimpleDateFormat("dd.MM.yyyy");
        calendar = Calendar.getInstance();
    }

    public String getTodayDate() {
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    public String getYesterdayDate() {
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    public String addDay(String date) {
        try {
            calendar.setTime(sdf.parse(date));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Date day = calendar.getTime();
        return sdf.format(day);
    }
}
