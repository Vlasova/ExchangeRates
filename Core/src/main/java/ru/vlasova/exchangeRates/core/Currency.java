package ru.vlasova.exchangeRates.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Алина on 12.10.2016.
 */
public class Currency {

    private CurrenciesNames name;
    private Float exchange;

    public Currency() {
        this.name = CurrenciesNames.RUB;
        exchange = 1.0f;
    }

    public Currency(CurrenciesNames name, String date) {
        this.name = name;
        HTMLParser parser = new HTMLParser(date);
        exchange = new BigDecimal(parser.getExchangeByName(name)).setScale(4, RoundingMode.HALF_UP).floatValue();
    }

    public Float getExchange() {
        return exchange;
    }

    public CurrenciesNames getName() {
        return name;
    }

    public String getRussianName() {
        return name.getRussianName();
    }

    public boolean isTodayHigher() {
        HTMLParser parser = new HTMLParser(new Day().getYesterdayDate());
        Float yesterdayExchange = parser.getExchangeByName(name);
        return exchange > yesterdayExchange;
    }
}

