package ru.vlasova.exchangeRates.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by Алина on 15.10.2016.
 */
public class HTMLParser {
    private Elements table;

    public HTMLParser(String date) {
        String url = "http://www.cbr.ru/currency_base/daily.aspx?date_req=" + date;
        try {
            Document doc = Jsoup.connect(url).get();
            table = doc.getElementsByTag("tr");
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public Float getExchangeByName(CurrenciesNames name) {
        String stringExchange = null;
        int number = 0;
        if(name != CurrenciesNames.RUB) {
            for (int i = 1; i < table.size(); i++) {
                Elements currency = table.get(i).select("td");
                if (currency.get(1).text().equals(name.toString())) {
                    number = Integer.valueOf(currency.get(2).text());
                    String str = currency.get(4).text();
                    stringExchange = str.replace(',', '.');
                    break;
                }
            }
        }
        else {
            stringExchange = "1";
            number = 1;
        }
        Float exchange = Float.valueOf(stringExchange) / number;
        return exchange;
    }
}
