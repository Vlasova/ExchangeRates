package ru.vlasova.exchangeRates.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса приложения
 */
public class ExchangeRates implements ExchangeRatesAPI {

    @Override
    public double getExchange(CurrenciesNames name) {
        Currency currency = new Currency(name, Day.getTodayDate());
        return currency.getExchange();
    }

    @Override
    public double getExchange(CurrenciesNames name, String date) {
        Currency currency = new Currency(name, date);
        return currency.getExchange();
    }

    @Override
    public List<Currency> getAllExchanges() {
        List<Currency> allExchanges = new ArrayList<>();
        for(CurrenciesNames name: CurrenciesNames.values()) {
            allExchanges.add(new Currency(name, Day.getTodayDate()));
        }
        return  allExchanges;
    }

    @Override
    public List<Currency> getAllExchanges(String date) {
        List<Currency> allExchanges = new ArrayList<>();
        for(CurrenciesNames name: CurrenciesNames.values()) {
            allExchanges.add(new Currency(name, date));
        }
        return allExchanges;
    }

    @Override
    public double convert(CurrenciesNames originalName, CurrenciesNames finalName, double number) {
        Currency originalCurrency = new Currency(originalName, Day.getTodayDate());
        Currency finalCurrency = new Currency(finalName, Day.getTodayDate());
        double result = originalCurrency.getExchange() / finalCurrency.getExchange() * number;
        return (new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    @Override
    public List<Currency> getStatistics(CurrenciesNames name, String period) {
        List<Currency> statistics = new ArrayList<>();
        if(period.equals("за неделю")) {
            String firstDate = Day.getWeekAgoDate();
            String lastDate = Day.getTodayDate();
            statistics.add(new Currency(name, firstDate));
            while (!firstDate.equals(lastDate)) {
                firstDate = Day.getNextDate(firstDate);
                statistics.add(new Currency(name, firstDate));
            }
        }

        if(period.equals("за месяц")){
            String firstDate = Day.getMonthAgoDate(Day.getTodayDate());
            String lastDate = Day.getTodayDate();
            statistics.add(new Currency(name,firstDate));
            int i = 1;
            while(!firstDate.equals(lastDate)){
                firstDate = Day.getNextDate(firstDate);
                if((i%3)==0)
                    statistics.add(new Currency(name,firstDate));
                i++;
            }
        }

        if(period.equals("за год")){
            String firstDate = Day.getYearAgoDate();
            String lastDate = Day.getTodayDate();
            statistics.add(new Currency(name, firstDate));
            for(int i=0; i<12; i++){
                firstDate = Day.getMonthPlusDate(firstDate);
                statistics.add(new Currency(name, firstDate));
            }
        }
        return statistics;
    }
}


