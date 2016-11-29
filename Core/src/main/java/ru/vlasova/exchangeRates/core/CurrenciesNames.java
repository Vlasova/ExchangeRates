package ru.vlasova.exchangeRates.core;
//todo странная структура проекта: кажется, что Core должно быть в src/main/java и т.д

import ru.vlasova.exchangeRates.core.Exceptions.NoSuchCurrencyException;

/**
 * Перечисление доступных валют
 */
public enum CurrenciesNames {

    RUB("Российский рубль"),
    USD("Доллар США"),
    EUR("Евро"),
    JPY("Японская йена"),
    GBP("Английский фунт"),
    CHF("Швейцарский франк"),
    CAD("Канадский доллар"),
    AUD("Австралийский доллар");

    private String name;

    CurrenciesNames(String name){
        this.name = name;
    }

    static public CurrenciesNames getName(String name) throws NoSuchCurrencyException {
        for(CurrenciesNames currency: CurrenciesNames.values()) {
            if(currency.toString().equalsIgnoreCase(name))
                return currency;
        }
        throw new NoSuchCurrencyException("Неизвестная валюта");
    }

    public String getRussianName() {
        return name;
    }
}


