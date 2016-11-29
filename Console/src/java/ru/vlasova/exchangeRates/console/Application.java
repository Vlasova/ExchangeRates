package ru.vlasova.exchangeRates.console;

import ru.vlasova.exchangeRates.core.CurrenciesNames;
import ru.vlasova.exchangeRates.core.Currency;
import ru.vlasova.exchangeRates.core.ExchangeRates;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Консольное приложение
 */
public class Application {
    private ExchangeRates exchangeRates = new ExchangeRates();
    private Scanner in = new Scanner(System.in);

    /**
     * Считать команду пользователя
     */
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

    /**
     * Вывести курс на сегодня
     */
    public void printToday() {
        try {
            System.out.print("Введите код валюты: ");
            CurrenciesNames name = CurrenciesNames.getName(in.nextLine().trim());
            System.out.println(name.getRussianName());
            System.out.println("Курс на сегодня: " + exchangeRates.getExchange(name));
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Вывести курсы всех валют на сегодня
     */
    public void printAllToday() {
        List<Currency> exchanges = exchangeRates.getAllExchanges();
        for(int i=1; i<exchanges.size(); i++) {
            System.out.println(exchanges.get(i).getName() + " " + exchanges.get(i).getExchange());
        }
    }

    /**
     * Вывести курс валюты на заданную дату
     */
    public void printByDate() {
        try {
            System.out.print("Введите код валюты: ");
            CurrenciesNames name = CurrenciesNames.getName(in.nextLine().trim());
            System.out.print("Введите дату: ");
            String date = in.nextLine().trim();
            System.out.println(exchangeRates.getExchange(name, date));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Вывести курсы всех валют на заданную дату
     */
    public void printAllByDate() {
        try {
            System.out.print("Введите дату: ");
            String date = in.nextLine().trim();
            List<Currency> exchanges = exchangeRates.getAllExchanges(date);
            for(int i =1; i<exchanges.size(); i++) {
                System.out.println(exchanges.get(i).getName() + " " + exchanges.get(i).getExchange());
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Конвертировать валюту
     */
    public void convert() {
        try {
            System.out.print("Введите код начальной валюты: ");
            CurrenciesNames originalName = CurrenciesNames.getName(in.nextLine().trim());
            System.out.print("Введите код конечной валюты: ");
            CurrenciesNames finalName = CurrenciesNames.getName(in.nextLine().trim());
            System.out.print("Введите сумму: ");
            double number = in.nextDouble();
            System.out.println(number + " " + originalName + " = " +
                    exchangeRates.convert(originalName, finalName, number) + " " + finalName);
        } catch(InputMismatchException e) {
            System.out.println("Некорректная сумма");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Вывести описание команд
     */
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
