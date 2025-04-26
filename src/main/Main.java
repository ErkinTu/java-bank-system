package main;

import controller.BankController;
import controller.ClientController;
import controller.PaymentController;
import service.BankServiceImpl;
import service.ClientServiceImpl;
import service.PaymentMethod.BankAccountPaymentMethod;
import service.PaymentMethod.PaymentMethod;
import service.PaymentMethod.PhonePaymentMethod;
import view.MainView;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Инициализация сервисов и контроллеров
        ClientServiceImpl clientService = new ClientServiceImpl("data/clients.csv");
        ClientController clientController = new ClientController(clientService);

        BankServiceImpl bankService = new BankServiceImpl("data/banks.csv");
        BankController bankController = new BankController(bankService);

        MainView mainView = getMainView(clientController, bankController);
        mainView.setVisible(true);
    }

    private static MainView getMainView(ClientController clientController, BankController bankController) {
        BankAccountPaymentMethod bankAccountPaymentMethod = new BankAccountPaymentMethod();
        PhonePaymentMethod phonePaymentMethod = new PhonePaymentMethod();
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(bankAccountPaymentMethod);
        paymentMethods.add(phonePaymentMethod);

        PaymentController paymentController = new PaymentController(paymentMethods);

        // Создание и отображение главного окна приложения
        MainView mainView = new MainView(clientController, bankController, paymentController);
        return mainView;
    }
}