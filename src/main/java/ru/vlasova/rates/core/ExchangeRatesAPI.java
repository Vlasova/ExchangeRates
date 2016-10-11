package ru.vlasova.rates.core;

public interface ExchangeRatesAPI {

    void getTodayExchange(CurrenciesNames currency);
    void getAllTodayExchanges();
    void changeExchange(CurrenciesNames currency);
    boolean isHigher(CurrenciesNames currency);
    void convert(CurrenciesNames originalCurrency, CurrenciesNames finalCurrency, int number);
    void getStatistics(CurrenciesNames currency, int time);

}
