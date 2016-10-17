package ru.vlasova.exchangeRates.core;

import java.util.Hashtable;

/**
 * Created by Алина on 15.10.2016.
 */
public class ExchangeRates implements ExchangeRatesAPI{

    @Override
    public float getTodayExchange(CurrenciesNames name) {
        Currency currency = new Currency(name);
        return currency.getExchange();
    }

    @Override
    public Hashtable<CurrenciesNames, Float> getAllTodayExchanges() {
        ExchangeTable table = new ExchangeTable();
        return table.getExchangeTable();
    }

    @Override
    public float getExchangeByDate(CurrenciesNames name, String date) {
        Currency currency = new Currency(name, date);
        return currency.getExchange();
    }

    @Override
    public Hashtable<CurrenciesNames, Float> getAllExchangesByDate(String date) {
        ExchangeTable table = new ExchangeTable(date);
        return table.getExchangeTable();
    }

    @Override
    public boolean isHigher(CurrenciesNames currency) {
        return false;
    }

    @Override
    public void convert(CurrenciesNames originalCurrency, CurrenciesNames finalCurrency, int number) {

    }

    @Override
    public void getStatistics(CurrenciesNames currency, int time) {

    }
}


