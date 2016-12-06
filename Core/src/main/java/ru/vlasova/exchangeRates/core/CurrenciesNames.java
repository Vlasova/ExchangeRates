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
    AUD("Австралийский доллар"),
    AZN("Азербайджанский манат"),
    AMD("Армянский драм"),
    BYN("Белорусский рубль"),
    BGN("Болгарский лев"),
    BRL("Бразильский реал"),
    HUF("Венгерский форинт"),
    KRW("Вон Республики Корея"),
    DKK("Датская крона"),
    INR("Индийская рупия"),
    KZT("Казахстанский тенге"),
    KGS("Киргизский сом"),
    CNY("Китайский юань"),
    MDL("Молдавский лей"),
    TMT("Новый туркменский манат"),
    NOK("Норвежская крона"),
    PLN("Польский злотый"),
    RON("Румынский лей"),
    SGD("Сингапурский доллар"),
    TJS("Таджикский сомони"),
    TRY("Турецкая лира"),
    UZS("Узбекский сум"),
    UAH("Украинская гривна"),
    CZK("Чешская крона"),
    SEK("Шведская крона"),
    ZAR("Южноафриканский рэнд");

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


