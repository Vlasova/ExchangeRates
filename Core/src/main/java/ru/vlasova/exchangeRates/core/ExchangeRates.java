package ru.vlasova.exchangeRates.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by Алина on 15.10.2016.
 */
public class ExchangeRates implements ExchangeRatesAPI{

    private SimpleDateFormat sdf;
    private Date date;
    private String todayDate;

    public ExchangeRates() {
        sdf = new SimpleDateFormat("dd.MM.yyyy");
        date = new Date();
        todayDate = sdf.format(date);
    }

    @Override
    public Float getTodayExchange(CurrenciesNames name) {
        Currency currency = new Currency(name, todayDate);
        return currency.getExchange();
    }

    @Override
    public Hashtable<CurrenciesNames, Float> getAllTodayExchanges() {
        ExchangeTable table = new ExchangeTable(todayDate);
        return table.getExchangeTable();
    }

    @Override
    public Float getExchangeByDate(CurrenciesNames name, String date) {
        Currency currency = new Currency(name, date);
        return currency.getExchange();
    }

    @Override
    public Hashtable<CurrenciesNames, Float> getAllExchangesByDate(String date) {
        ExchangeTable table = new ExchangeTable(date);
        return table.getExchangeTable();
    }

    @Override
    public boolean isHigher(CurrenciesNames name) {
        Long time = date.getTime();
        time -= (24*60*60*1000);
        String yesterdayDate = sdf.format(new Date(time));
        Currency todayCurrency = new Currency(name, todayDate);
        Currency yesterdayCurrency = new Currency(name, yesterdayDate);
        return todayCurrency.getExchange() > yesterdayCurrency.getExchange();
    }

    @Override
    public Float convert(CurrenciesNames originalName, CurrenciesNames finalName, int number) {
        Currency originalCurrency = new Currency(originalName, todayDate);
        Currency finalCurrency = new Currency(finalName, todayDate);
        Float inRubles = originalCurrency.getExchange();
        Float result = Float.valueOf(inRubles / finalCurrency.getExchange() * number);
        return (new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).floatValue());
    }

    @Override
    public void getStatistics(CurrenciesNames name, int time) {

    }
}


