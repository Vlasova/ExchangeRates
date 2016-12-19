package ru.vlasova.exchangeRates.GUI;

import ru.vlasova.exchangeRates.core.Day;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by Алина on 02.12.2016.
 */
public class ExchangeRatesGUI extends  JFrame{
        ExchangeRatesGUI() {
            setTitle("Exchange Rates");
            setSize(1000, 600);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

            setContentPane(new RatesPanel());
            Container container = getContentPane();
            container.setLayout(new BorderLayout());

            JPanel panel = new Rates();
            setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
            container.add(panel);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new ExchangeRatesGUI();
                }
            });
        }
}

class RatesPanel extends JPanel {
    public void paintComponent(Graphics g) {
        URL path = RatesPanel.class.getResource("/background.jpg");
        ImageIcon background = new ImageIcon(path);
        g.drawImage(background.getImage(), 0, 0, this);
    }
}
