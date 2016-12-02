package ru.vlasova.exchangeRates.GUI;

import ru.vlasova.exchangeRates.core.Currency;
import ru.vlasova.exchangeRates.core.ExchangeRates;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алина on 02.12.2016.
 */
public class Rates extends JPanel{

    ExchangeRates exchangeRates = new ExchangeRates();

    Rates() {
        setLayout(new BorderLayout());

        JTable ratesTable = createTable();
        add(new JScrollPane(ratesTable), BorderLayout.CENTER);
    }

        private JTable createTable() {
        RatesTableModel ratesTableModel = new RatesTableModel(exchangeRates.getAllExchanges());
        JTable ratesTable = new JTable(ratesTableModel);
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumn column = null;
        for(int i=0; i<ratesTable.getColumnCount(); i++) {
            column = ratesTable.getColumnModel().getColumn(i);
            if(i == 3) {
                column.setMinWidth(350);
                column.setCellRenderer(centerRender);
            }
            else {
                column.setCellRenderer(centerRender);
                column.setMinWidth(100);
            }
            column.setResizable(false);
        }
        ratesTable.setRowHeight(40);
        return ratesTable;
    }


    private class RatesTableModel extends AbstractTableModel {

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
            switch(columnIndex) {
                case 1:
                    obj = currency.getName();
                    break;
                case 2:
                    obj = currency.getNumberOfUnits();
                    break;
                case 3:
                    obj = currency.getRussianName();
                    break;
                case 4:
                    obj = currency.getExchange();
                    break;
            }
            return obj;
        }

        public String getColumnName(int columnIndex) {
            String str = null;
            switch (columnIndex) {
                case 0:
                    str = "";
                    break;
                case 1:
                    str = "Валюта";
                    break;
                case 2:
                    str = "Номинал";
                    break;
                case 3:
                    str = "Наименование";
                    break;
                case 4:
                    str = "Курс";
                    break;
            }
            return str;
        }
    }
}
