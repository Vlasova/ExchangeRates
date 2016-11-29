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

    public Currency(CurrenciesNames name, String date) {
        this.name = name;
        this.date = date;
        if(name.equals(CurrenciesNames.RUB)) {
            exchange = 1;
        } else {
            HTMLParser parser = new HTMLParser(date);
            exchange = new BigDecimal(parser.getExchangeByName(name)).setScale(4, RoundingMode.HALF_UP).doubleValue();
        }
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
}

