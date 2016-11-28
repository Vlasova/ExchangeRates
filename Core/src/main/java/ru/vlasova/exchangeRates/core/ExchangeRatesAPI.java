package ru.vlasova.exchangeRates.core;

import java.util.List;

/**
 * Интерфейс приложения
 */
public interface ExchangeRatesAPI {

    /**
     * Получить курс валюты на сегодня
     * @param name название валюты
     * @return курс валюты
     */
    float getTodayExchange(CurrenciesNames name);

    /**
     * Получить курсы всех валют на сегодня
     * @return курсы валют
     */
    List<Currency> getAllTodayExchanges();

    /**
     * Получить курс валюты на заданную дату
     * @param name название валюты
     * @param date дата
     * @return курс валюты
     */
    // todo может быть, перегрузить метод getTodayExchange(CurrenciesNames name)
    float getExchangeByDate(CurrenciesNames name, String date);

    /**
     * Получить курсы всех валют на заданную дату
     * @param date дата
     * @return курсы валют
     */
    // todo может быть, перегрузить метод List<Currency> getAllTodayExchanges().
    List<Currency> getAllExchangesByDate(String date);

    /**
     * Конвертировать валюту
     * @param originalCurrency начальная валюта
     * @param finalCurrency конечная валюта
     * @param number количество
     * @return стоимость в конечной валюте
     */
    float convert(CurrenciesNames originalCurrency, CurrenciesNames finalCurrency, float number);

    /**
     * Получить статистику изменения курса за указанный период
     * @param currency валюта
     * @param firstDate начальная дата
     * @param lastDate конечная дата
     * @return статистика
     */
    List<Currency> getStatistics(CurrenciesNames currency, String firstDate, String lastDate);
}