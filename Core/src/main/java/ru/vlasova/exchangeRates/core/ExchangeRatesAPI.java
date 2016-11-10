package ru.vlasova.exchangeRates.core;

import java.util.Vector;

public interface ExchangeRatesAPI {

    float getTodayExchange(CurrenciesNames name);
    Vector<Currency> getAllTodayExchanges();
    float getExchangeByDate(CurrenciesNames name, String date);
    Vector<Currency> getAllExchangesByDate(String date);
    float convert(CurrenciesNames originalCurrency, CurrenciesNames finalCurrency, float number);
    Vector<Currency> getStatistics(CurrenciesNames currency, String firstDate, String lastDate);
}