package ru.vlasova.exchangeRates.GUI;

import ru.vlasova.exchangeRates.core.Day;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by Алина on 02.12.2016.
 */
public class ExchangeRatesGUI extends  JFrame{
    private JPanel panel;
        ExchangeRatesGUI() {
            setTitle("Exchange Rates");
            setSize(1000, 600);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
            Font font = new Font("verdana", Font.PLAIN, 14);

            setContentPane(new RatesPanel());
            Container container = getContentPane();
            container.setLayout(new FlowLayout(FlowLayout.CENTER));

            panel = new Rates();
            panel.setPreferredSize(new Dimension(1000, 800));

            JButton buttonRates = new JButton("Курсы валют");
            buttonRates.setPreferredSize(new Dimension(330, 40));
            buttonRates.setBackground(Color.black);
            buttonRates.setFont(font);
            buttonRates.setForeground(Color.white);
            buttonRates.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(panel);
                    panel = new Rates();
                    panel.setPreferredSize(new Dimension(1000, 600));
                    add(panel);
                    repaint();
                    revalidate();
                }
            });

            JButton buttonStatistics = new JButton("Статистика");
            buttonStatistics.setPreferredSize(new Dimension(330, 40));
            buttonStatistics.setBackground(Color.black);
            buttonStatistics.setFont(font);
            buttonStatistics.setForeground(Color.white);
            buttonStatistics.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(panel);
                    panel = new Statistics();
                    add(panel);
                    repaint();
                    revalidate();
                }
            });

            JButton buttonConverter = new JButton("Конвертер валют");
            buttonConverter.setPreferredSize(new Dimension(330, 40));
            buttonConverter.setBackground(Color.black);
            buttonConverter.setFont(font);
            buttonConverter.setForeground(Color.white);
            buttonConverter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(panel);
                    panel = new Converter();
                    panel.setPreferredSize(new Dimension(1000, 600));
                    add(panel);
                    repaint();
                    revalidate();
                }
            });

            JToolBar toolBar = new JToolBar();
            toolBar.setLayout(new BorderLayout());
            toolBar.add(buttonRates, BorderLayout.WEST);
            toolBar.add(buttonStatistics, BorderLayout.CENTER);
            toolBar.add(buttonConverter, BorderLayout.EAST);
            toolBar.setPreferredSize(new Dimension(1020, 45));
            toolBar.setOpaque(false);
            add(toolBar);

            add(panel);
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
        URL path = RatesPanel.class.getResource("/background.png");
        ImageIcon background = new ImageIcon(path);
        g.drawImage(background.getImage(), 0, 0, this);
    }
}
