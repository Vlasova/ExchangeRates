package ru.vlasova.exchangeRates.core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.vlasova.exchangeRates.core.Exceptions.IllegalDateFormatException;

/**
 * Класс для получения курсов валют с сайта cbr.ru
 */
public class HTMLParser {
    private Elements table;

    public HTMLParser(String date) throws IllegalDateFormatException {
        String url = "http://www.cbr.ru/currency_base/daily.aspx?date_req=" + Day.getDate(date);
        try {
            Document doc = Jsoup.connect(url).get();
            table = doc.getElementsByTag("tr");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Получить строку из таблицы для указанной валюты
     * @param name название валюты
     * @return строка
     */
    private Elements getElements(CurrenciesNames name) {
        Elements elements = null;
        for (int i=1; i<table.size(); i++) {
            elements = table.get(i).select("td");
            if (elements.get(1).text().equals(name.toString()))
                break;
        }
        return elements;
    }

    /**
     * Получить курс валюты по ее названию
     * @param name название валюты
     * @return курс
     */
    public double getExchangeByName(CurrenciesNames name) {
        String stringExchange = null;
        if(name != CurrenciesNames.RUB) {
            Elements currency = getElements(name);
            String str = currency.get(4).text();
            stringExchange = str.replace(',', '.');
        }
        else {
            stringExchange = "1";
        }
        return Double.valueOf(stringExchange);
    }

    /**
     * Получить количество единиц валюты
     * @param name название валюты
     * @return количество единиц
     */

    public int getNumberOfUnits(CurrenciesNames name) {
        int numberOfUnits;
        if(name != CurrenciesNames.RUB) {
            Elements currency = getElements(name);
            numberOfUnits = Integer.valueOf(currency.get(2).text());
        }
        else {
            numberOfUnits = 1;
        }
        return  numberOfUnits;
    }
}
