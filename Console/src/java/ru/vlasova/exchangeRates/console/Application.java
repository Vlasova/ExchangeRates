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
        boolean process = true;
        while(process) {
            System.out.print("Введите команду: ");
            String command = in.nextLine();
            switch (command.trim()) {
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
                    printAllByDate();
                    break;
                case "перевод":
                    convert();
                    break;
                case "помощь":
                    printHelp();
                    break;
                case "выход":
                    process = false;
                    break;
                default:
                    System.out.println("Неизвестная команда");
                    break;
            }
        }
    }

    private String readDate() throws Exception{
        try {
            String date = in.nextLine().trim();
            Day day = new Day();
            return day.getDate(date);
        } catch(Exception e) {
            throw e;
        }
    }

    private CurrenciesNames readName() throws Exception {
        try {
            String code = in.nextLine().trim().toUpperCase();
            return CurrenciesNames.getName(code);
        } catch(Exception e) {
            throw e;
        }
    }

    private float readNumber() throws Exception {
        try {
            return Float.valueOf(in.nextLine());
        } catch(Exception e) {
            throw new Exception("Некорректная сумма");
        }
    }

    public void printToday() {
        try {
            System.out.print("Введите код валюты: ");
            CurrenciesNames name = readName();
            System.out.println(name.getRussianName());
            System.out.println("Курс на сегодня: " + exchangeRates.getTodayExchange(name));
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void printAllToday() {
        Vector<Currency> exchanges = exchangeRates.getAllTodayExchanges();
        for(int i=0; i<exchanges.size(); i++) {
            System.out.println(exchanges.get(i).getName() + " " + exchanges.get(i).getExchange());
        }
    }

    public void printByDate() {
        try {
            System.out.print("Введите код валюты: ");
            CurrenciesNames name = readName();
            System.out.print("Введите дату: ");
            String date = readDate();
            System.out.println("Курс " + name + " на " + date);
            System.out.println(exchangeRates.getExchangeByDate(name, date));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printAllByDate() {
        try {
            System.out.print("Введите дату: ");
            String date = readDate();
            Vector<Currency> exchanges = exchangeRates.getAllExchangesByDate(date);
            for(int i =0; i<exchanges.size(); i++) {
                System.out.println(exchanges.get(i).getName() + " " + exchanges.get(i).getExchange());
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void convert() {
        try {
            System.out.print("Введите код начальной валюты: ");
            CurrenciesNames originalName = readName();
            System.out.print("Введите код конечной валюты: ");
            CurrenciesNames finalName = readName();
            System.out.print("Введите сумму: ");
            float number = readNumber();
            System.out.println(number + " " + originalName + " = " +
                    exchangeRates.convert(originalName, finalName, number) + " " + finalName);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void printHelp() {
        System.out.println("Используйте следующие команды: ");
        System.out.println("    на сегодня - вывести сегодняшний курс заданной валюты");
        System.out.println("    все на сегодня - вывести сегодняшние курсы всех доступных валют");
        System.out.println("    на дату - вывести курс валюты на заданную дату");
        System.out.println("    все на дату - вывести курсы всех доступных валют на заданную дату");
        System.out.println("    перевод - конвертировать валюты");
        System.out.println("    выход - выйти из приложения");
        System.out.println("На данный момент доступны следующие валюты: ");
        for(CurrenciesNames name: CurrenciesNames.values()) {
            System.out.println("    " + name + " " + name.getRussianName());
        }
    }
}
