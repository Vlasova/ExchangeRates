package ru.vlasova.exchangeRates.core;

import java.util.Vector;

public interface ExchangeRatesAPI {

    Float getTodayExchange(CurrenciesNames name);
    Vector<Currency> getAllTodayExchanges();
    Float getExchangeByDate(CurrenciesNames name, String date);
    Vector<Currency> getAllExchangesByDate(String date);
    Float convert(CurrenciesNames originalCurrency, CurrenciesNames finalCurrency, int number);
    Vector<Currency> getStatistics(CurrenciesNames currency, String firstDate, String lastDate);
}