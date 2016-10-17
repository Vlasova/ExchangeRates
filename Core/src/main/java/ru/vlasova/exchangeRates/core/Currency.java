package ru.vlasova.exchangeRates.core;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Алина on 12.10.2016.
 */
public class Currency {

    private CurrenciesNames name;
    private float exchange = 0;
    private String date = null;

    public Currency() {
        this.name = CurrenciesNames.RUB;
        exchange = 1.0f;
    }

    public Currency(CurrenciesNames name) {
        this.name = name;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        date = sdf.format(new Date()).toString();
        HTMLParser parser = new HTMLParser(date);
        exchange = parser.getExchangeByName(name);
    }

    public Currency(CurrenciesNames name, String date) {
        this.name = name;
        HTMLParser parser = new HTMLParser(date);
        exchange = parser.getExchangeByName(name);
    }

    public float getExchange() {
        return exchange;
    }

    public CurrenciesNames getName() {
        return name;
    }

    public String getRussianName() {
        return name.getName();
    }

}

