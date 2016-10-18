package ru.vlasova.exchangeRates.core;

import java.util.Hashtable;

public interface ExchangeRatesAPI {

    Float getTodayExchange(CurrenciesNames name);
    Hashtable<CurrenciesNames, Float> getAllTodayExchanges();
    Float getExchangeByDate(CurrenciesNames name, String date);
    Hashtable<CurrenciesNames, Float> getAllExchangesByDate(String date);
    boolean isHigher(CurrenciesNames currency);
    Float convert(CurrenciesNames originalCurrency, CurrenciesNames finalCurrency, int number);
    void getStatistics(CurrenciesNames currency, int time);
}