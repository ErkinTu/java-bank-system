package main;

import controller.BankController;
import controller.ClientController;
import controller.PaymentController;
import service.PaymentMethod.BankAccountCashbackPaymentMethod;
import service.impl.BankServiceImpl;
import service.impl.ClientServiceImpl;
import service.PaymentMethod.BankAccountPaymentMethod;
import service.PaymentMethod.PaymentMethod;
import service.PaymentMethod.PhonePaymentMethod;
import service.impl.PaymentServiceImpl;
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
        PaymentServiceImpl paymentService = new PaymentServiceImpl();
        BankAccountPaymentMethod bankAccountPaymentMethod = new BankAccountPaymentMethod();
        PhonePaymentMethod phonePaymentMethod = new PhonePaymentMethod();
        BankAccountCashbackPaymentMethod bankAccountCashbackPaymentMethod = new BankAccountCashbackPaymentMethod();
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        paymentMethods.add(bankAccountPaymentMethod);
        paymentMethods.add(phonePaymentMethod);
        paymentMethods.add(bankAccountCashbackPaymentMethod);

        List<PaymentServiceImpl> paymentServices = new ArrayList<>();
        paymentServices.add(paymentService);

        PaymentController paymentController = new PaymentController(paymentMethods, paymentServices);

        // Создание и отображение главного окна приложения
        MainView mainView = new MainView(clientController, bankController, paymentController);
        return mainView;
    }
}