package ru.vlasova.exchangeRates.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Класс для работы с датами
 */
public class Day {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private Calendar calendar = Calendar.getInstance();

    /**
     * Получить сегодняшнюю дату
     * @return сегодняшняя дата
     */
    public String getTodayDate() {
        Date date = calendar.getTime();
        return sdf.format(date);
        // todo return sdf.format(calendar.getTime()); (можно сразу возвращать везде)
    }

    /**
     * Получить вчерашнюю дату
     * @return вчерашняя дата
     */
    public String getYesterdayDate() {
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    /**
     * Получить дату, следующую за указанной
     * @param date дата
     * @return следующая дата
     */
    public String getNextDay(String date) {
        try {
            calendar.setTime(sdf.parse(date));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        catch (Exception e) {
            e.printStackTrace(); // todo почему тут не бросает дальше, как в методе ниже
        }
        Date day = calendar.getTime();
        return sdf.format(day);
    }

    /**
     * Получить отформатированную дату
     * @param day дата
     * @return отформатированная дата
     * @throws Exception при неверно заданной дате
     */
    public String getDate(String day) throws Exception {
        try {
            Date date = sdf.parse(day);
            return sdf.format(date);
        } catch(Exception e) {
            throw new Exception("Неверный формат даты");
        }
    }
}
