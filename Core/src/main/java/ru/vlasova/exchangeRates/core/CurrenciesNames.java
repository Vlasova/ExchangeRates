package ru.vlasova.exchangeRates.core;
//todo странная структура проекта: кажется, что Core должно быть в src/main/java и т.д

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

    static public CurrenciesNames getName(String name) throws Exception { // todo создать везде свое исключение
        for(CurrenciesNames currency: CurrenciesNames.values()) {
            if(currency.toString().equals(name)) // todo может быть, equalsIgnoreCase();
                return currency;
        }
        throw new Exception("Неизвестная валюта");
    }

    public String getRussianName() {
        return name;
    }
}


