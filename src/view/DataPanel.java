package view;

import model.Bank;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DataPanel extends JPanel {
    private JTable dataTable;
    private DefaultTableModel tableModel;
    
    public DataPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    
    public void showClientsData(List<Client> clients) {
        String[] columnNames = {"Имя", "Фамилия", "Дата рождения", "ИНН", "Телефон", "Банковский счет", "Баланс"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        for (Client client : clients) {
            tableModel.addRow(new Object[]{
                    client.getFirstName(),
                    client.getLastName(),
                    client.getDateOfBirth(),
                    client.getInn(),
                    client.getPhoneNumber(),
                    client.getBankAccount(),
                    client.getBalance()
            });
        }
        
        updateTableData(tableModel, "Список клиентов");
    }
    
    public void showBanksData(List<Bank> banks) {
        String[] columnNames = {"Название", "Код", "Адрес", "Баланс"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        for (Bank bank : banks) {
            tableModel.addRow(new Object[]{
                    bank.getName(),
                    bank.getCode(),
                    bank.getAddress(),
                    bank.getBalance()
            });
        }
        
        updateTableData(tableModel, "Список банков");
    }
    
    private void updateTableData(DefaultTableModel model, String title) {
        removeAll();
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(51, 102, 204));
        
        dataTable = new JTable(model);
        dataTable.setRowHeight(25);
        dataTable.setShowGrid(true);
        dataTable.getTableHeader().setBackground(new Color(240, 240, 240));
        dataTable.getTableHeader().setForeground(new Color(51, 102, 204));
        
        JScrollPane scrollPane = new JScrollPane(dataTable);
        
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.add(titleLabel);
        
        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        revalidate();
        repaint();
    }
}
