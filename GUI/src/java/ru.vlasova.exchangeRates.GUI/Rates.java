package ru.vlasova.exchangeRates.GUI;

import ru.vlasova.exchangeRates.core.Currency;
import ru.vlasova.exchangeRates.core.Day;
import ru.vlasova.exchangeRates.core.Exceptions.IllegalDateFormatException;
import ru.vlasova.exchangeRates.core.ExchangeRates;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алина on 02.12.2016.
 */
public class Rates extends JPanel {

    ExchangeRates exchangeRates = new ExchangeRates();

    Rates() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        Font font = new Font("verdana", Font.PLAIN, 14);

        final JComboBox<String> days = getDayComboBox();
        days.setPreferredSize(new Dimension(130, 25));
        days.setBackground(new Color(35,35,35));
        days.setFont(font);
        days.setForeground(Color.white);
        add(days);

        final JComboBox<String> months = getMonthComboBox();
        months.setPreferredSize(new Dimension(130, 25));
        months.setBackground(new Color(35,35,35));
        months.setFont(font);
        months.setForeground(Color.white);
        add(months);

        final JComboBox<String> years = getYearComboBox();
        years.setPreferredSize(new Dimension(130, 25));
        years.setBackground(new Color(35,35,35));
        years.setFont(font);
        years.setForeground(Color.white);
        add(years);

        final JTable ratesTable = new JTable(new RatesTableModel(exchangeRates.getAllExchanges(), Day.getTodayDate()));
        createTable(ratesTable);
        JScrollPane scrollPane = new JScrollPane(ratesTable);
        scrollPane.setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(990, 480));
        scrollPane.getViewport().setOpaque(false);

        JButton button = new JButton("Изменить дату");
        button.setPreferredSize(new Dimension(200, 25));
        button.setBackground(new Color(0,150,0));
        button.setFont(font);
        button.setForeground(Color.white);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String date = days.getSelectedItem().toString() + "." + months.getSelectedItem().toString() + "." + years.getSelectedItem().toString();
                    Day.getDate(date);
                    ratesTable.setModel(new RatesTableModel(exchangeRates.getAllExchanges(date), date));
                    createTable(ratesTable);
                }catch(IllegalDateFormatException exc){
                    JOptionPane.showMessageDialog(null, exc.getMessage());
                }

            }
        });
        add(button);
        add(scrollPane);

    }

    private void createTable(JTable ratesTable) {

        ratesTable.setRowHeight(40);
        ratesTable.setOpaque(false);
        ratesTable.setIntercellSpacing(new Dimension(5, 5));
        ratesTable.setShowHorizontalLines(false);
        ratesTable.setShowVerticalLines(false);

        ratesTable.getTableHeader().setPreferredSize(new Dimension(0, 0));

        CellRenderer render = new CellRenderer();
        TableColumn column = null;
        for (int i = 0; i < ratesTable.getColumnCount(); i++) {
            column = ratesTable.getColumnModel().getColumn(i);
            column.setCellRenderer(render);
            if (i == 3) {
                column.setMinWidth(350);
            } else {
                column.setMinWidth(100);
            }
            column.setResizable(false);
        }
    }

    private JComboBox<String> getYearComboBox() {
        String[] years = new String[12];
        years[0]="год";
        for(int i=1; i<=Day.getYear()-2005; i++){
            years[i]=Integer.toString(i+2005);
        }
        return new JComboBox<>(years);
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
        return new JComboBox<>(months);
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
        return new JComboBox<>(days);
    }

    class RatesTableModel extends AbstractTableModel {

        private List<Currency> rates = new ArrayList<>();
        private String date;

        RatesTableModel(List<Currency> currencies, String date) {
            rates = currencies;
            this.date = date;
        }

        public int getColumnCount() {
            return 5;
        }

        public int getRowCount() {
            return rates.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Currency currency = rates.get(rowIndex);
            Object obj = new Object();
            switch (columnIndex) {
                case 0:
                    if (rowIndex == 0)
                        obj = new String(date);
                    else
                        obj = new String("/" + currency.getName() + ".png");
                    break;
                case 1:
                    if (rowIndex == 0)
                        obj = new String("Валюта");
                    else
                        obj = currency.getName();
                    break;
                case 2:
                    if (rowIndex == 0)
                        obj = new String("Номинал");
                    else
                        obj = currency.getNumberOfUnits();
                    break;
                case 3:
                    if (rowIndex == 0)
                        obj = new String("Наименование");
                    else
                        obj = currency.getRussianName();
                    break;
                case 4:
                    if (rowIndex == 0)
                        obj = new String("Курс");
                    else
                        obj = currency.getExchange();
                    break;
            }
            return obj;
        }
    }

    class CellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel();
            label.setOpaque(true);
            label.setHorizontalAlignment(JLabel.CENTER);

            Font font = new Font("verdana", Font.PLAIN, 16);
            label.setFont(font);
            label.setForeground(Color.WHITE);

            if(column == 0 && row != 0) {
                URL path = CellRenderer.class.getResource(value.toString());
                ImageIcon icon = new ImageIcon(path);
                label.setIcon(icon);
                label.setSize(50, 50);
                label.setBackground(new Color(105,105,105,100));
            }
            else {
                if(row == 0){
                    label.setBackground(new Color(70,70,70,100));
                    label.setText(value.toString());
                }
                else{
                    label.setText(value.toString());
                    label.setBackground(new Color(105,105,105,100));

            }}
            return label;
        }
    }

}


