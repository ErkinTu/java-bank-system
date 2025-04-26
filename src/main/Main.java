package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import model.Bank;
import model.Client;
import controller.ClientController;
import controller.BankController;
import controller.PaymentController;
import service.ClientServiceImpl;
import service.BankServiceImpl;
import service.PaymentMethod.BankAccountPaymentMethod;
import service.PaymentMethod.PaymentMethod;
import service.PaymentMethod.PhonePaymentMethod;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // Initial services and controllers
        ClientServiceImpl clientService = new ClientServiceImpl("data/clients.csv");
        ClientController clientController = new ClientController(clientService);

        BankServiceImpl bankService = new BankServiceImpl("data/banks.csv");
        BankController bankController = new BankController(bankService);

        BankAccountPaymentMethod bankAccountPaymentMethod = new BankAccountPaymentMethod();
        PhonePaymentMethod phonePaymentMethod = new PhonePaymentMethod();
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(bankAccountPaymentMethod);
        paymentMethods.add(phonePaymentMethod);

        PaymentController paymentController = new PaymentController(paymentMethods);

        // Main FRAME
        JFrame frame = new JFrame();
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Navigation Bar
        JButton clientListButton = new JButton("Список Клиентов");
        JButton bankListButton = new JButton("Список Банков");

        JPanel navBar = new JPanel();
        navBar.setBackground(new Color(245, 245, 220));
        navBar.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));
        navBar.add(clientListButton);
        navBar.add(bankListButton);

        // Transfer (Control) Panel
        String[] items = {"Клиент 1", "Клиент 2", "Клиент 3", "Клиент 4"};
        JComboBox<String> fromBox = new JComboBox<>(items);
        JComboBox<String> toBox = new JComboBox<>(items);

        JTextField amountField = new JTextField(10);
        JButton transferButton = new JButton("Перевести");

        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
        controlPanel.setBackground(new Color(245, 245, 220));
        controlPanel.add(new JLabel("Отправитель:"));
        controlPanel.add(fromBox);
        controlPanel.add(new JLabel("Получатель:"));
        controlPanel.add(toBox);
        controlPanel.add(new JLabel("Сумма:"));
        controlPanel.add(amountField);
        controlPanel.add(transferButton);

        // Temp Panel
        JPanel tempPanel = new JPanel();

        // Clients Table
        List<Client> clients = clientController.getClientList();

        String[] clientsColumnNames = {"firstName", "lastName", "dateOfBirth", "inn", "phoneNumber", "bankAccount", "balance"};
        DefaultTableModel clientTableModel = new DefaultTableModel(clientsColumnNames, 0);

        for (Client client : clients) {
            clientTableModel.addRow(new Object[]{
                    client.getFirstName(),
                    client.getLastName(),
                    client.getDateOfBirth(),
                    client.getInn(),
                    client.getPhoneNumber(),
                    client.getBankAccount(),
                    client.getBalance()
            });
        }

        JTable clientsTable = new JTable(clientTableModel);
        JScrollPane clientsScrollPane = new JScrollPane(clientsTable);

        tempPanel.add(clientsScrollPane, BorderLayout.NORTH);

        // Banks Table
        List<Bank> banks = bankController.getBanks();

        String[] banksColumnNames = {"name", "code", "address", "balance"};
        DefaultTableModel bankTableModel = new DefaultTableModel(banksColumnNames, 0);

        for (Bank bank : banks) {
            bankTableModel.addRow(new Object[]{
                    bank.getName(),
                    bank.getCode(),
                    bank.getAddress(),
                    bank.getBalance()
            });
        }

        JTable banksTable = new JTable(bankTableModel);
        JScrollPane banksScrollPane = new JScrollPane(banksTable);

        tempPanel.add(banksScrollPane, BorderLayout.SOUTH);

        // Initial all Panel
        frame.add(navBar, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.add(tempPanel, BorderLayout.CENTER);


        // Show FRAME
        frame.setVisible(true);
    }
}
