package ru.vlasova.exchangeRates.GUI;

import ru.vlasova.exchangeRates.core.*;
import ru.vlasova.exchangeRates.core.Currency;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Statistics extends JPanel {
    ExchangeRates exchangeRates = new ExchangeRates();
    private Font font = new Font("verdana", Font.PLAIN, 14);

    Statistics() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(1000, 800));

        final JComboBox<String> currency = new JComboBox<>(getCurrencies());
        currency.setPreferredSize(new Dimension(70, 25));
        currency.setBackground(new Color(35, 35, 35));
        currency.setFont(font);
        currency.setForeground(Color.white);

        JLabel selectCurrency = new JLabel("Динамика курса");
        selectCurrency.setPreferredSize(new Dimension(200, 30));
        selectCurrency.setHorizontalAlignment(JLabel.RIGHT);
        selectCurrency.setFont(font);
        selectCurrency.setForeground(Color.white);

        final JRadioButton boxWeek = new JRadioButton("за неделю");
        boxWeek.setPreferredSize(new Dimension(110, 25));
        boxWeek.setOpaque(false);
        boxWeek.setFont(font);
        boxWeek.setForeground(Color.white);

        final JRadioButton boxMonth = new JRadioButton("за месяц");
        boxMonth.setPreferredSize(new Dimension(100, 25));
        boxMonth.setOpaque(false);
        boxMonth.setFont(font);
        boxMonth.setForeground(Color.white);

        final JRadioButton boxYear = new JRadioButton("за год");
        boxYear.setPreferredSize(new Dimension(100, 25));
        boxYear.setOpaque(false);
        boxYear.setFont(font);
        boxYear.setForeground(Color.white);

        ButtonGroup group = new ButtonGroup();
        group.add(boxWeek);
        group.add(boxMonth);
        group.add(boxYear);
        boxWeek.setSelected(true);

        final JTable table = new JTable(new StatisticsTableModel(exchangeRates.getStatistics(CurrenciesNames.USD, "за неделю"), CurrenciesNames.USD, "за неделю"));
        createTable(table);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(990, 500));
        scrollPane.getViewport().setOpaque(false);

        JButton button = new JButton("получить");
        button.setPreferredSize(new Dimension(150, 25));
        button.setFont(font);
        button.setForeground(Color.white);
        button.setBackground(new Color(0, 150, 0));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Currency> currencies = new ArrayList<Currency>();
                if (boxWeek.isSelected()) {
                    currencies = exchangeRates.getStatistics(CurrenciesNames.getName(currency.getSelectedItem().toString()), boxWeek.getText());
                    table.setModel(new StatisticsTableModel(currencies, CurrenciesNames.getName(currency.getSelectedItem().toString()), boxWeek.getText()));
                    createTable(table);
                }

                if (boxMonth.isSelected()) {
                    currencies = exchangeRates.getStatistics(CurrenciesNames.getName(currency.getSelectedItem().toString()), boxMonth.getText());
                    table.setModel(new StatisticsTableModel(currencies, CurrenciesNames.getName(currency.getSelectedItem().toString()), boxMonth.getText()));
                    createTable(table);
                }

                if(boxYear.isSelected()) {
                    currencies = exchangeRates.getStatistics(CurrenciesNames.getName(currency.getSelectedItem().toString()), boxYear.getText());
                    table.setModel(new StatisticsTableModel(currencies, CurrenciesNames.getName(currency.getSelectedItem().toString()), boxYear.getText()));
                    createTable(table);
                }
            }
        });

        add(selectCurrency);
        add(currency);
        add(boxWeek);
        add(boxMonth);
        add(boxYear);
        add(button);
        add(scrollPane);
    }

    private String[] getCurrencies() {
        String[] currencies = new String[CurrenciesNames.values().length - 1];
        for (int i = 0; i < CurrenciesNames.values().length - 1; i++)
            currencies[i] = CurrenciesNames.values()[i + 1].toString();
        return currencies;
    }

    private void createTable(JTable table) {
        table.setRowHeight(40);
        table.setPreferredSize(new Dimension(990, 600));
        table.setOpaque(false);
        table.setIntercellSpacing(new Dimension(5, 5));
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.getTableHeader().setPreferredSize(new Dimension(0, 0));

        TableColumn column = null;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            column.setCellRenderer(new CellRender());
            if (i == 2) {
                column.setMinWidth(250);
            } else {
                column.setMinWidth(350);
            }
            column.setResizable(false);
        }
    }

    class StatisticsTableModel extends AbstractTableModel {
        private List<Currency> statistics = new ArrayList<>();
        private CurrenciesNames name;
        private String period;

        StatisticsTableModel(List<Currency> statistics, CurrenciesNames name, String period) {
            this.statistics = statistics;
            this.name = name;
            this.period = period;
        }

        public int getColumnCount() {
            return 3;
        }

        public int getRowCount() {
            return statistics.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Currency currency = statistics.get(rowIndex);
            Object obj = new Object();
            switch (columnIndex) {
                case 0:
                    if (rowIndex == 0)
                        obj = new String("Дата");
                    else
                        obj = currency.getDate();
                    break;
                case 1:
                    if (rowIndex == 0)
                        obj = new String("Курс");
                    else
                        obj = currency.getExchange();
                    break;
                case 2:
                    if (rowIndex == 0)
                        obj = new String("");
                    else {
                        if (period.equals("за неделю")) {
                            if (currency.isHigher())
                                obj = new String("+" + currency.getDifference());
                            else
                                obj = currency.getDifference();
                        }
                        if (period.equals("за месяц")) {
                            if (currency.get3DaysDifference() > 0)
                                obj = new String("+" + currency.get3DaysDifference());
                            else
                                obj = currency.get3DaysDifference();
                        }
                        if(period.equals("за год")){
                            if(currency.getMonthDifference() > 0)
                                obj = new String("+" + currency.getMonthDifference());
                            else
                                obj = currency.getMonthDifference();
                        }
                    }
                    break;
            }
            return obj;
            }
        }
    }

        class CellRender extends DefaultTableCellRenderer {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setHorizontalAlignment(JLabel.CENTER);

                Font font = new Font("verdana", Font.PLAIN, 16);
                label.setFont(font);
                label.setForeground(Color.WHITE);

                if (row == 0) {
                    label.setBackground(new Color(70, 70, 70, 100));
                    label.setText(value.toString());
                } else {
                    if (column == 2) {
                        if (Double.valueOf(value.toString()) > 0) {
                            label.setForeground(Color.GREEN);
                            label.setText(value.toString());
                        }
                        if (Double.valueOf(value.toString()) < 0) {
                            label.setForeground(Color.red);
                            label.setText(value.toString());
                        }
                        label.setBackground(new Color(105, 105, 105, 100));
                    } else
                        label.setBackground(new Color(105, 105, 105, 100));
                    label.setText(value.toString());
                }
                return label;
            }
        }