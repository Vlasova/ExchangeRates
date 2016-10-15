package ru.vlasova.exchangeRates.core;

import java.util.Hashtable;

/**
 * Created by Алина on 15.10.2016.
 */
public class ExchangeRates implements ExchangeRatesAPI{

    private TodayExchanges todayExchanges = new TodayExchanges();

    @Override
    public Float getTodayExchange(CurrenciesNames name) {
        return todayExchanges.getExchangeByName(name);
    }

    @Override
    public Hashtable<CurrenciesNames, Float> getAllTodayExchanges() {

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


