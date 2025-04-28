package controller;

import model.Client;
import service.PaymentMethod.PaymentMethod;
import service.impl.PaymentServiceImpl;
import model.dto.TransferResult;
import model.dto.PaymentResult;
import service.interfaces.PaymentService;


import java.util.ArrayList;
import java.util.List;

public class PaymentController {
    private final List<PaymentMethod> availableMethods;
    private final PaymentService paymentService;

    public PaymentController(List<PaymentMethod> paymentMethodsList, List<PaymentServiceImpl> paymentServicesList) {
        this.availableMethods = new ArrayList<>(paymentMethodsList);
        this.paymentService = new PaymentServiceImpl();
    }

    public List<PaymentMethod> getAvailableMethods() {
        return new ArrayList<>(availableMethods);
    }

    public PaymentResult processPayment(Client sender, Client receiver, double amount, PaymentMethod paymentMethod) {
        TransferResult result = paymentService.executeTransfer(sender, receiver, amount, paymentMethod);
        return new PaymentResult(result.isSuccess(), result.getMessage());
    }
}