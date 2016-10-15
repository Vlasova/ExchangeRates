package ru.vlasova.exchangeRates.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Алина on 15.10.2016.
 */
public class HTMLParser {
    private String url = "http://www.cbr.ru/currency_base/daily.aspx?date_req=15.10.2016";
    private Elements links;

    public HTMLParser() {
        try {
            Document doc = Jsoup.connect(url).get();
            links = doc.getElementsByTag("tr");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String getExchangeByName(CurrenciesNames name) {
        String exchange = "";
        Elements string;
        for (int i=1; i<links.size(); i++) {
            string = links.get(i).select("td");
            if(string.get(1).text().equals(name.toString())){
                exchange = string.get(4).text();
                break;
            }
        }
        return exchange;
    }
}
