package ru.vlasova.exchangeRates.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса приложения
 */
public class ExchangeRates implements ExchangeRatesAPI{

    private Day day = new Day();

    @Override
    public float getTodayExchange(CurrenciesNames name) {
        Currency currency = new Currency(name, day.getTodayDate());
        return currency.getExchange();
    }

    @Override
    public List<Currency> getAllTodayExchanges() {
        List<Currency> allExchanges = new ArrayList<>();
        for(CurrenciesNames name: CurrenciesNames.values()) {
            if(!name.equals(CurrenciesNames.RUB))
                allExchanges.add(new Currency(name, day.getTodayDate()));
        }
        return  allExchanges;
    }

    @Override
    public float getExchangeByDate(CurrenciesNames name, String date) {
        Currency currency = new Currency(name, date);
        return currency.getExchange();
    }

    @Override
    public List<Currency> getAllExchangesByDate(String date) {
        List<Currency> allExchange = new ArrayList<>();
        for(CurrenciesNames name: CurrenciesNames.values()) {
            if(!name.equals(CurrenciesNames.RUB))
                allExchange.add(new Currency(name, date));
        }
        return allExchange;
    }

    @Override
    public float convert(CurrenciesNames originalName, CurrenciesNames finalName, float number) {
        Currency originalCurrency = new Currency(originalName, day.getTodayDate());
        Currency finalCurrency = new Currency(finalName, day.getTodayDate());
        float inRubles = originalCurrency.getExchange();
        float result = Float.valueOf(inRubles / finalCurrency.getExchange() * number);
        return (new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).floatValue());
    }

    @Override
    public List<Currency> getStatistics(CurrenciesNames name, String firstDate, String lastDate) {
        List<Currency> statistics = new ArrayList<>();
        statistics.add(new Currency(name, firstDate));
        while(!firstDate.equals(lastDate)) {
            firstDate = day.getNextDay(firstDate);
            statistics.add(new Currency(name, firstDate));
        }
        return statistics;
    }
}


