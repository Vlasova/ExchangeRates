package ru.vlasova.exchangeRates.GUI;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import ru.vlasova.exchangeRates.core.CurrenciesNames;
import ru.vlasova.exchangeRates.core.Day;
import ru.vlasova.exchangeRates.core.ExchangeRates;

import javax.swing.*;
import java.awt.*;

public class Statistics extends JPanel {
    ExchangeRates exchangeRates = new ExchangeRates();
    private Font font = new Font("verdana", Font.PLAIN, 16);

    Statistics() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(900, 400));

        final JComboBox<CurrenciesNames> currency = new JComboBox<>(CurrenciesNames.values());
        currency.setPreferredSize(new Dimension(70, 30));
        currency.setBackground(new Color(35,35,35));
        currency.setFont(font);
        currency.setForeground(Color.white);

        JLabel selectCurrency = new JLabel("Ввыберете валюту");
        selectCurrency.setPreferredSize(new Dimension(450, 100));
        selectCurrency.setHorizontalAlignment(JLabel.RIGHT);
        selectCurrency.setFont(font);
        selectCurrency.setForeground(Color.white);

        JLabel begin = new JLabel("Начальная дата");
        begin.setPreferredSize(new Dimension(400,100));
        begin.setHorizontalAlignment(JLabel.RIGHT);
        begin.setFont(font);
        begin.setForeground(Color.white);

        JLabel end = new JLabel("Конечная дата");
        end.setPreferredSize(new Dimension(100,100));
        end.setFont(font);
        end.setForeground(Color.white);

        final JComboBox<String> sinceDay = getDayComboBox();
        final JComboBox<String> sinceMonth = getMonthComboBox();
        final JComboBox<String> sinceYear = getYearComboBox();
        final JComboBox<String> toDay = getDayComboBox();
        final JComboBox<String> toMonth = getMonthComboBox();
        final JComboBox<String> toYear = getYearComboBox();

        add(selectCurrency);
        add(currency);
        add(begin);
        add(sinceDay);
        add(sinceMonth);
        add(sinceYear);
        add(end);
        add(toDay);
        add(toMonth);
        add(toYear);

    }

    /**private JFreeChart createGraf(){

        DateAxis dateAxic = new DateAxis("Дата");
        dateAxic.setPositiveArrowVisible(true);

        NumberAxis rateAxic = new NumberAxis("Курс");
        rateAxic.setPositiveArrowVisible(true);
    }*/

    private JComboBox<String> getYearComboBox() {
        String[] years = new String[12];
        years[0]="год";
        for(int i = 1; i<= Day.getYear()-2005; i++){
            years[i]=Integer.toString(i+2005);
        }
        JComboBox<String> box = new JComboBox<>(years);
        box.setPreferredSize(new Dimension(80, 25));
        box.setBackground(new Color(35,35,35));
        box.setFont(font);
        box.setForeground(Color.white);
        return box;
    }

    private JComboBox<String> getMonthComboBox(){
        String[] months = new String[13];
        months[0] = "месяц";
        for(int i=1; i<=12; i++){
            if(i<10)
                months[i]="0"+Integer.toString(i);
            else
                months[i]=Integer.toString(i);
        }
        JComboBox<String> box = new JComboBox<>(months);
        box.setPreferredSize(new Dimension(80, 25));
        box.setBackground(new Color(35,35,35));
        box.setFont(font);
        box.setForeground(Color.white);
        return box;
    }

    private JComboBox<String> getDayComboBox(){
        String[] days = new String[32];
        days[0] = "день";
        for(int i=1; i<=31; i++){
            if(i<10)
                days[i]="0"+Integer.toString(i);
            else
                days[i]=Integer.toString(i);
        }
        JComboBox<String> box = new JComboBox<>(days);
        box.setPreferredSize(new Dimension(80, 25));
        box.setBackground(new Color(35,35,35));
        box.setFont(font);
        box.setForeground(Color.white);
        return box;
    }
}