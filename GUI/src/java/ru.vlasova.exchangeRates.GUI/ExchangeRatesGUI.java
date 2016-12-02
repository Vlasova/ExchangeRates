package ru.vlasova.exchangeRates.GUI;

import javax.swing.*;

/**
 * Created by Алина on 02.12.2016.
 */
public class ExchangeRatesGUI {
        ExchangeRatesGUI() {
            JFrame frame = new JFrame("Exchange Rates");
            frame.setSize(1000, 600);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            JPanel rates = new Rates();
            frame.add(rates);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new ExchangeRatesGUI();
                }
            });
        }
}
