package ru.vlasova.rates.core;

/**
 * Created by Алина on 12.10.2016.
 */
public class Currency {

    private CurrenciesNames name;
    private float exchange;

    public Currency(CurrenciesNames name) {
        this.name = name;
    }

    public Currency() {
        this.name = CurrenciesNames.RUB;
    }

    public void setExchange(float exchange) {
        this.exchange = exchange;
    }

    public float getExchange() {
        return exchange;
    }

    public CurrenciesNames getName() {
        return name;
    }

    public String getRussianName() {
        return name.getName();
    }

}
