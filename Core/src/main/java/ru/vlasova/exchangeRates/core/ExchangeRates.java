package ru.vlasova.exchangeRates.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Vector;

/**
 * Created by Алина on 15.10.2016.
 */
public class ExchangeRates implements ExchangeRatesAPI{

    private Day day;

    public ExchangeRates() {
        day = new Day();
    }

    @Override
    public Float getTodayExchange(CurrenciesNames name) {
        Currency currency = new Currency(name, day.getTodayDate());
        return currency.getExchange();
    }

    @Override
    public Vector<Currency> getAllTodayExchanges() {
        Vector<Currency> allExchanges = new Vector<>();
        for(CurrenciesNames name: CurrenciesNames.values()) {
            allExchanges.add(new Currency(name, day.getTodayDate()));
        }
        return  allExchanges;
    }

    @Override
    public Float getExchangeByDate(CurrenciesNames name, String date) {
        Currency currency = new Currency(name, date);
        return currency.getExchange();
    }

    @Override
    public Vector<Currency> getAllExchangesByDate(String date) {
        Vector<Currency> allExchange = new Vector<>();
        for(CurrenciesNames name: CurrenciesNames.values()) {
            allExchange.add(new Currency(name, date));
        }
        return allExchange;
    }

    @Override
    public Float convert(CurrenciesNames originalName, CurrenciesNames finalName, int number) {
        Currency originalCurrency = new Currency(originalName, day.getTodayDate());
        Currency finalCurrency = new Currency(finalName, day.getTodayDate());
        Float inRubles = originalCurrency.getExchange();
        Float result = Float.valueOf(inRubles / finalCurrency.getExchange() * number);
        return (new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).floatValue());
    }

    @Override
    public Vector<Currency> getStatistics(CurrenciesNames name, String firstDate, String lastDate) {
        Vector<Currency> statistics = new Vector<>();
        statistics.add(new Currency(name, firstDate));
        while(!firstDate.equals(lastDate)) {
            firstDate = day.addDay(firstDate);
            statistics.add(new Currency(name, firstDate));
        }
        return statistics;
    }
}


