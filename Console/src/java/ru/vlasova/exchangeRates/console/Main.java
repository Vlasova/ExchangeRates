package ru.vlasova.exchangeRates.console;

public class Main {
    // todo такой метод можно унести прямо в Application
    public static void main(String[] args) {
        Application app = new Application();
        app.readCommand();
    }
}
