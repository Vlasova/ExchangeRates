package ru.vlasova.exchangeRates.GUI;

import ru.vlasova.exchangeRates.core.CurrenciesNames;
import ru.vlasova.exchangeRates.core.ExchangeRates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Converter extends JPanel{
    ExchangeRates exchangeRates = new ExchangeRates();
    Converter(){
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        Font font = new Font("verdana", Font.PLAIN, 16);

        JLabel label = new JLabel("Конвертация по курсу ЦБ на сегодня");
        label.setFont(font);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(990, 100));
        label.setForeground(Color.white);
        add(label);

        final JTextField originalField = new JTextField(15);
        originalField.setFont(font);
        originalField.setBackground(new Color(50,50,50));
        originalField.setForeground(Color.white);
        originalField.setPreferredSize(new Dimension(100, 40));
        originalField.setHorizontalAlignment(JTextField.CENTER);

        final JTextField finalField = new JTextField(15);
        finalField.setFont(font);
        finalField.setBackground(new Color(50,50,50));
        finalField.setForeground(Color.white);
        finalField.setPreferredSize(new Dimension(100, 40));
        finalField.setHorizontalAlignment(JTextField.CENTER);

        final JComboBox<CurrenciesNames> originalCurrency = new JComboBox<>(CurrenciesNames.values());
        originalCurrency.setPreferredSize(new Dimension(130, 40));
        originalCurrency.setBackground(new Color(35,35,35));
        originalCurrency.setFont(font);
        originalCurrency.setForeground(Color.white);

        final JComboBox<CurrenciesNames> finalCurrency = new JComboBox<>(CurrenciesNames.values());
        finalCurrency.setPreferredSize(new Dimension(130, 40));
        finalCurrency.setBackground(new Color(35,35,35));
        finalCurrency.setFont(font);
        finalCurrency.setForeground(Color.white);

        JButton button = new JButton();
        button.setPreferredSize(new Dimension(60,40));
        URL path = Converter.class.getResource("/arrow.jpg");
        button.setIcon(new ImageIcon(path));
        button.setBorderPainted(false);
        button.setFont(font);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    CurrenciesNames originalName = (CurrenciesNames) originalCurrency.getSelectedItem();
                    CurrenciesNames finalName = (CurrenciesNames) finalCurrency.getSelectedItem();
                    double number = Double.parseDouble(originalField.getText().replace(',','.'));
                    double rezult = exchangeRates.convert(originalName, finalName, number);
                    finalField.setText(Double.toString(rezult));
                }catch(Exception exp){
                    JOptionPane.showMessageDialog(null, "Неверный формат суммы");
                }
            }
        });

        add(originalField);
        add(originalCurrency);
        add(button);
        add(finalField);
        add(finalCurrency);

    }
}
