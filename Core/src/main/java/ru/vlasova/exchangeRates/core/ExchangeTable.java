package ru.vlasova.exchangeRates.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by Алина on 15.10.2016.
 */
public class ExchangeTable {

    private Hashtable<CurrenciesNames, Float> exchangeTable = new Hashtable<>();
    private String date = null;

    public ExchangeTable() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        date = sdf.format(new Date()).toString();
        HTMLParser parser = new HTMLParser(date);
        completeTable(parser);
    }

    public ExchangeTable(String date) {
        HTMLParser parser = new HTMLParser(date);
        completeTable(parser);
    }

    private void completeTable(HTMLParser parser) {
        for(CurrenciesNames name: CurrenciesNames.values()) {
            if(name != CurrenciesNames.RUB)
                exchangeTable.put(name, parser.getExchangeByName(name));
        }
    }

    public Hashtable<CurrenciesNames, Float> getExchangeTable() {
        return exchangeTable;
    }

    public Float getExchangeByName(CurrenciesNames name) {
        return exchangeTable.get(name);
    }
}
