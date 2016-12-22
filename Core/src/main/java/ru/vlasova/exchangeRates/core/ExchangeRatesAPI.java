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
    double getExchange(CurrenciesNames name);

    /**
     * Получить курс валюты на заданную дату
     * @param name название валюты
     * @param date дата
     * @return курс валюты
     */

    double getExchange(CurrenciesNames name, String date);

    /**
     * Получить курсы всех валют на сегодня
     * @return курсы валют
     */
    List<Currency> getAllExchanges();

    /**
     * Получить курсы всех валют на заданную дату
     * @param date дата
     * @return курсы валют
     */

    List<Currency> getAllExchanges(String date);

    /**
     * Конвертировать валюту
     * @param originalCurrency начальная валюта
     * @param finalCurrency конечная валюта
     * @param number количество
     * @return стоимость в конечной валюте
     */
    double convert(CurrenciesNames originalCurrency, CurrenciesNames finalCurrency, double number);

    /**
     * Получить статистику изменения курса за указанный период
     * @param currency валюта
     * @param period период
     * @return статистика
     */
    List<Currency> getStatistics(CurrenciesNames currency, String period);
}