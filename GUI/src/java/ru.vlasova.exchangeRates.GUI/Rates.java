package ru.vlasova.exchangeRates.GUI;

import ru.vlasova.exchangeRates.core.Currency;
import ru.vlasova.exchangeRates.core.Day;
import ru.vlasova.exchangeRates.core.ExchangeRates;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
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
        setLayout(new BorderLayout());

        JTable ratesTable = createTable();
        JScrollPane scrollPane = new JScrollPane(ratesTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane);
    }

    private JTable createTable() {
        List<Currency> currencies = exchangeRates.getAllExchanges();
        RatesTableModel ratesTableModel = new RatesTableModel(currencies);
        JTable ratesTable = new JTable(ratesTableModel);

        ratesTable.setRowHeight(40);
        ratesTable.setOpaque(false);
        ratesTable.setIntercellSpacing(new Dimension(5, 5));
        ratesTable.setShowVerticalLines(false);
        ratesTable.setShowHorizontalLines(false);

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
        return ratesTable;
    }

    class RatesTableModel extends AbstractTableModel {

        private List<Currency> rates = new ArrayList<>();

        RatesTableModel(List<Currency> currencies) {
            rates = currencies;
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
                        obj = new String("");
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

            if (column == 0 && row != 0) {
                URL path = CellRenderer.class.getResource(value.toString());
                ImageIcon icon = new ImageIcon(path);
                label.setIcon(icon);
                label.setSize(50, 50);
                label.setBackground(new Color(255, 255, 255, 70));
            }
            else {
                label.setText(value.toString());
                if(row == 0)
                    label.setBackground(new Color(255, 228, 181));
                else
                    label.setBackground(new Color(255, 255, 255, 70));
            }
            return label;
        }
    }
}


