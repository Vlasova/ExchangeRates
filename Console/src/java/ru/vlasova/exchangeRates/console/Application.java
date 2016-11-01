package ru.vlasova.exchangeRates.console;

import ru.vlasova.exchangeRates.core.CurrenciesNames;
import ru.vlasova.exchangeRates.core.Currency;
import ru.vlasova.exchangeRates.core.Day;
import ru.vlasova.exchangeRates.core.ExchangeRates;

import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Алина on 31.10.2016.
 */

public class Application {
    private ExchangeRates exchangeRates = new ExchangeRates();
    private Scanner in = new Scanner(System.in);

    public void readCommand() {
        System.out.print("Введите команду: ");
        String command =in.nextLine();
        switch(command) {
            case "на сегодня":
                printToday();
                break;
            case "все на сегодня":
                printAllToday();
                break;
            case "на дату":
                printByDate();
                break;
            case "все на дату":
                break;
            case "перевод":
                break;
            case "статистика":
                break;
            case "помощь":
                break;
            case "выход":
                break;
            default:
                break;
        }
    }

    public void printToday() {
        System.out.print("Введите код валюты: ");
        String code = in.nextLine();
        try {
            CurrenciesNames name = CurrenciesNames.getName(code);
            System.out.println(name.getRussianName());
            System.out.println("Курс на сегодня: " + exchangeRates.getTodayExchange(name));
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        finally {
            readCommand();
        }
    }

    public void printAllToday() {
        Vector<Currency> exchanges = exchangeRates.getAllTodayExchanges();
        for(int i=0; i<exchanges.size(); i++) {
            System.out.println(exchanges.get(i).getName() + " " + exchanges.get(i).getExchange());
        }
        readCommand();
    }

    public void printByDate() {
        try {
            System.out.print("Введите дату: ");
            String date = in.nextLine();
            Day day = new Day();
            String formattedDate = day.getDate(date);
            System.out.print("Введите код валюты: ");
            String code = in.nextLine();
            CurrenciesNames name = CurrenciesNames.getName(code);
            System.out.println("Курс на " + formattedDate);
            System.out.println(exchangeRates.getExchangeByDate(name, date));
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            readCommand();
        }
    }
}
