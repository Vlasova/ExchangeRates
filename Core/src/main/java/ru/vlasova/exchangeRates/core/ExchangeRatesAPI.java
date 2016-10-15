package ru.vlasova.exchangeRates.core;

import java.util.Hashtable;

public interface ExchangeRatesAPI {

    Float getTodayExchange(CurrenciesNames name);
    Hashtable<CurrenciesNames, Float> getAllTodayExchanges();
    boolean isHigher(CurrenciesNames currency);
    void convert(CurrenciesNames originalCurrency, CurrenciesNames finalCurrency, int number);
    void getStatistics(CurrenciesNames currency, int time);

}