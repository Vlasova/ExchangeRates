package ru.vlasova.translator;

import java.util.*;

public class Translator{
    public static void main(String[] args){
        Translator translator = new Translator();
        System.out.print("Введите количество дюймов: ");
        Scanner in = new Scanner(System.in);
        System.out.print(translator.convert(in.nextInt()) + "см");

    }
    double convert(int inches){
        return (inches*2.54);
    }
}