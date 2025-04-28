package view;

import controller.BankController;
import controller.ClientController;
import controller.PaymentController;
import model.Client;
import model.dto.PaymentResult;
import service.PaymentMethod.PaymentMethod;
import service.interfaces.PaymentService;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private final ClientController clientController;
    private final BankController bankController;
    private final PaymentController paymentController;

    private NavigationPanel navigationPanel;
    private DataPanel dataPanel;
    private TransactionPanel transactionPanel;
    private StatusPanel statusPanel;

    public MainView(ClientController clientController, BankController bankController, PaymentController paymentController) {
        this.clientController = clientController;
        this.bankController = bankController;
        this.paymentController = paymentController;

        initializeUI();
        setupEventListeners();

        // Инициализация с таблицей клиентов по умолчанию
        showClientsData();
    }

    private void initializeUI() {
        // Настройка основного окна
        setTitle("Банковская система");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Создание панелей интерфейса
        navigationPanel = new NavigationPanel();
        dataPanel = new DataPanel();
        transactionPanel = new TransactionPanel(
                clientController.getClientList(),
                paymentController.getAvailableMethods()
        );
        statusPanel = new StatusPanel();

        // Добавление панелей на основное окно
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        mainPanel.add(navigationPanel, BorderLayout.WEST);
        mainPanel.add(dataPanel, BorderLayout.CENTER);
        mainPanel.add(transactionPanel, BorderLayout.EAST);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void setupEventListeners() {
        // Обработчики событий для навигационной панели
        navigationPanel.addClientsButtonListener(e -> showClientsData());
        navigationPanel.addBanksButtonListener(e -> showBanksData());
        navigationPanel.addSaveButtonListener(e -> saveData());

        // Обработчик события для кнопки перевода
        transactionPanel.addTransferButtonListener(e -> executeTransfer());
    }

    private void showClientsData() {
        dataPanel.showClientsData(clientController.getClientList());
    }

    private void showBanksData() {
        dataPanel.showBanksData(bankController.getBanks());
    }

    private void saveData() {
        clientController.saveClientList(clientController.getClientList());
        statusPanel.showSuccessMessage("Данные успешно сохранены");
    }

    private void executeTransfer() {
        Client sender = transactionPanel.getSelectedSender();
        Client receiver = transactionPanel.getSelectedReceiver();
        String amountText = transactionPanel.getAmount();
        PaymentMethod paymentMethod = transactionPanel.getSelectedPaymentMethod();
        // Hard coding
        // PaymentService paymentService = new PaymentServiceImpl();
        PaymentService paymentService = paymentController.getServiceByIndex(0);

        try {
            double amount = Double.parseDouble(amountText);

            PaymentResult result = paymentController.processPayment(
                    sender, receiver, amount, paymentMethod, paymentService);

            if (result.isSuccess()) {
                statusPanel.showSuccessMessage(result.getMessage());
                // Обновляем данные клиентов после успешного перевода
                showClientsData();
                // Обновляем списки клиентов в панели транзакций
                transactionPanel.updateClientsList(clientController.getClientList());
                // Очищаем поле суммы
                transactionPanel.clearAmountField();
            } else {
                statusPanel.showErrorMessage(result.getMessage());
            }

        } catch (NumberFormatException e) {
            statusPanel.showErrorMessage("Неверный формат суммы. Введите число.");
        }
    }
}