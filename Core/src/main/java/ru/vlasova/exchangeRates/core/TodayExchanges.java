package ru.vlasova.exchangeRates.core;

import java.util.Hashtable;

/**
 * Created by Алина on 15.10.2016.
 */
public class TodayExchanges {

    private Hashtable<CurrenciesNames, Float> tableExchanges = new Hashtable<>();
    HTMLParser parser = new HTMLParser();

    public TodayExchanges() {
        for(CurrenciesNames name: CurrenciesNames.values()) {
            tableExchanges.put(name, 1.2f);
        }
    }

    public Hashtable<CurrenciesNames, Float> getTableExchanges() {
        return tableExchanges;
    }

    public Float getExchangeByName(CurrenciesNames name) {
        return tableExchanges.get(name);
    }
}
