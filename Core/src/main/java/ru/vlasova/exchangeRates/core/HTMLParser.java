package ru.vlasova.exchangeRates.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Класс для получения курсов валют с сайта cbr.ru
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

    /**
     * Получить курс валюты по ее названию
     * @param name название валюты
     * @return курс
     */
    public float getExchangeByName(CurrenciesNames name) {
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
        return Float.valueOf(stringExchange)/number;
    }
}
