package ru.vlasova.exchangeRates.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ru.vlasova.exchangeRates.core.Exceptions.IllegalDateFormatException;

/**
 * Класс для работы с датами
 */
public class Day {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    /**
     * Получить сегодняшнюю дату
      * @return дата
     */
    public static String getTodayDate() {
        return sdf.format(Calendar.getInstance().getTime());
    }

    /**
     * Получить отформатированную дату
     * @param date дата
     * @return отформатированная дата
     * @throws IllegalDateFormatException при неверно заданной дате
     */
    public static String getDate(String date) throws IllegalDateFormatException {
        try {
            return sdf.format(sdf.parse(date));
        } catch(Exception e) {
            throw new IllegalDateFormatException("Неверный формат даты");
        }
    }

    /**
     * Получить предыдущую дату
     * @param date дата
     * @return предыдущая дата
     * @throws IllegalDateFormatException при неверно заданной дате
     */

    public static String getPastDate(String date) throws IllegalDateFormatException {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            return sdf.format(calendar.getTime());
        }catch(Exception e) {
            throw new IllegalDateFormatException("Неверный формат даты");
        }
    }

    /**
     * Получить вчерашнюю дату
     * @return вчерашняя дата
     */

    public static String getYeaterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return sdf.format(calendar.getTime()) ;
    }
    /**
     * Получить следующую дату
     * @param date дата
     * @return следующая дата
     * @throws IllegalDateFormatException при неверно заданной дате
     */

    public static String getNextDate(String date) throws IllegalDateFormatException {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            return sdf.format(calendar.getTime());
        }catch(Exception e) {
            throw new IllegalDateFormatException("Неверный формат даты");
        }
    }

    public static String getTommorowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return sdf.format(calendar.getTime());
    }
}
