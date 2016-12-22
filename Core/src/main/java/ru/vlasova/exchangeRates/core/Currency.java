package ru.vlasova.exchangeRates.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Класс валюта
 */
public class Currency {

    private CurrenciesNames name;
    private double exchange;
    private String date;
    private int numberOfUnits;

    public Currency(CurrenciesNames name, String date) {
        this.name = name;
        this.date = date;
        HTMLParser parser = new HTMLParser(date);
        exchange = new BigDecimal(parser.getExchangeByName(name)).setScale(4, RoundingMode.HALF_UP).doubleValue();
        numberOfUnits = parser.getNumberOfUnits(name);
    }

    /**
     * Получить стоимость валюты
     * @return стоимость
     */
    public double getExchange() {
        return exchange;
    }

    /**
     * Получить буквенный код валюты
     * @return код
     */
    public CurrenciesNames getName() {
        return name;
    }

    /**
     * Получить русское название валюты
     * @return название
     */
    public String getRussianName() {
        return name.getRussianName();
    }

    /**
     * Узнать, увеличилась ли стоимость валюты с предыдущего дня
     * @return true если курс повысился
     *         false если курс понизился (или не изменился)
     */

    public boolean isHigher() {
        HTMLParser pastDateParser = new HTMLParser(Day.getPastDate(date));
        return exchange > pastDateParser.getExchangeByName(name);
    }

    /**
     * Узнать, уменьшилась ли стоимость валюты с предыдущего дня
     * @return true если курс понизился
     *         false если курс повысился или не изменился
     */

    public boolean isLower() {
        HTMLParser pastDateParser = new HTMLParser(Day.getPastDate(date));
        return exchange < pastDateParser.getExchangeByName(name);
    }

    /**
     * Получить количество единиц валюты
     * @return количество единиц
     */

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public String getDate(){
        return date;
    }

    public double getDifference(){
        HTMLParser pastDateParser = new HTMLParser(Day.getPastDate(date));
        double difference = exchange - pastDateParser.getExchangeByName(name);
        return new BigDecimal(difference).setScale(4, RoundingMode.HALF_UP).doubleValue();
    }

    public double get3DaysDifference(){
        HTMLParser pastDateParser = new HTMLParser(Day.get3PastDate(date));
        double difference = exchange - pastDateParser.getExchangeByName(name);
        return new BigDecimal(difference).setScale(4, RoundingMode.HALF_UP).doubleValue();
    }

    public double getMonthDifference(){
        HTMLParser pastDateParser = new HTMLParser(Day.getMonthAgoDate(date));
        double difference = exchange - pastDateParser.getExchangeByName(name);
        return new BigDecimal(difference).setScale(4, RoundingMode.HALF_UP).doubleValue();
    }

}

