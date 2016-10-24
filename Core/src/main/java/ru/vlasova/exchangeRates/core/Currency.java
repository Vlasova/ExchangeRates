package ru.vlasova.exchangeRates.core;

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
        exchange = parser.getExchangeByName(name);
    }

    public Float getExchange() {
        return exchange;
    }

    public CurrenciesNames getName() {
        return name;
    }

    public String getRussianName() {
        return name.getName();
    }

    public boolean isTodayHigher() {
        HTMLParser parser = new HTMLParser(new Day().getYesterdayDate());
        Float yesterdayExchange = parser.getExchangeByName(name);
        return exchange > yesterdayExchange;
    }
}

