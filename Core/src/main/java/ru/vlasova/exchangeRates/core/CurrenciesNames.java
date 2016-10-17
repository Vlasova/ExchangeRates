package ru.vlasova.exchangeRates.core;

/**
 * Created by Алина on 11.10.2016.
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

    public String getName(){
        return name;
    }

}


