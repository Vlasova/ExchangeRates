package ru.vlasova.exchangeRates.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

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
            if(isCorrect(date))
                return date;
            else throw new IllegalDateFormatException("Неверный формат даты");
        } catch(Exception e) {
            throw new IllegalDateFormatException("Неверный формат даты");
        }
    }

    /**
     * Проверить формат даты
     * @param date дата
     * @return true если формат верный
     */

    private static boolean isCorrect(String date) {
        StringTokenizer tokenizer = new StringTokenizer(date, ".");
        String day = tokenizer.nextToken();
        String month = tokenizer.nextToken();
        String year = tokenizer.nextToken();
        if(Integer.parseInt(year)<2006 || Integer.parseInt(year)> GregorianCalendar.getInstance().get(Calendar.YEAR))
            return false;
        int intMonth = Integer.parseInt(month);
        if(Integer.parseInt(month) < 1 || Integer.parseInt(month)>12)
            return false;
        int intDay = Integer.parseInt(day);
        if(intDay<1)
            return false;
        int[] days = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
        for(int i=0; i<days.length; i++){
            if(intMonth==i && intDay>days[i-1])
                return false;
        }
        return true;
    }

    /**
     * Получить текущий год
     * @return год
     */

    public static int getYear(){
        GregorianCalendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.YEAR);
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

    public static String get3PastDate(String date) throws IllegalDateFormatException{
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            calendar.add(Calendar.DAY_OF_YEAR, -3);
            return sdf.format(calendar.getTime());
        }catch(Exception e){
            throw new IllegalDateFormatException("Неверный формат даты");
        }
    }

    public static String getMonthPlusDate(String date) throws IllegalDateFormatException{
        int[] days = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            int month = calendar.get(Calendar.MONTH);
            if(month==11)
                calendar.add(Calendar.DAY_OF_YEAR, 31);
            else
                calendar.add(Calendar.DAY_OF_YEAR, days[month]);
            return sdf.format(calendar.getTime());
        }catch(Exception e){
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

    public static String getWeekAgoDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        return sdf.format(calendar.getTime());
    }

    public static String getMonthAgoDate(String date) throws IllegalDateFormatException{
        try {
            int[] days = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            int month = calendar.get(Calendar.MONTH);
            if(month==0)
                calendar.add(Calendar.DAY_OF_YEAR, -31);
            else
                calendar.add(Calendar.DAY_OF_YEAR, -days[month - 1]);
            return sdf.format(calendar.getTime());
        }catch(Exception e){
            throw  new IllegalDateFormatException("Неверный формат даты");
        }
    }

    public static String getYearAgoDate(){
        Calendar calendar  = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -366);
        return sdf.format(calendar.getTime());
    }
}
