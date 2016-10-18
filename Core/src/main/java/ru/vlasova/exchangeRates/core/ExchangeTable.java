package ru.vlasova.exchangeRates.core;

import java.util.Hashtable;

/**
 * Created by Алина on 15.10.2016.
 */
public class ExchangeTable {

    private Hashtable<CurrenciesNames, Float> exchangeTable;
    private String date;

    public ExchangeTable(String date) {
        this.date = date;
        exchangeTable = new Hashtable<>();
        completeTable(date);
    }

    private void completeTable(String date) {
        for (CurrenciesNames name : CurrenciesNames.values()) {
            if (name != CurrenciesNames.RUB)
                exchangeTable.put(name, new Currency(name, date).getExchange());
        }
    }

    public Hashtable<CurrenciesNames, Float> getExchangeTable() {
        return exchangeTable;
    }

    public Float getExchangeByName(CurrenciesNames name) {
        return exchangeTable.get(name);
    }

    public String getDate() {
        return date;
    }
}
