package ru.vlasova.exchangeRates.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by Алина on 15.10.2016.
 */
public class HTMLParser {
    private Elements links;

    public HTMLParser(String date) {
        String url = "http://www.cbr.ru/currency_base/daily.aspx?date_req=" + date;
        try {
            Document doc = Jsoup.connect(url).get();
            links = doc.getElementsByTag("tr");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Float getExchangeByName(CurrenciesNames name) {
        String stringExchange = null;
        for (int i=1; i<links.size(); i++) {
            Elements currency = links.get(i).select("td");
            if(currency.get(1).text().equals(name.toString())){
                String str = currency.get(4).text();
                stringExchange = str.replace(',', '.');
                break;
            }
        }
        Float exchange = Float.valueOf(stringExchange);
        return exchange;
    }
}
