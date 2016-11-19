package ru.vlasova.exchangeRates.core;

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

    static public CurrenciesNames getName(String name) throws Exception {
        for(CurrenciesNames currency: CurrenciesNames.values()) {
            if(currency.toString().equals(name))
                return currency;
        }
        throw new Exception("Неизвестная валюта");
    }

    public String getRussianName() {
        return name;
    }
}


