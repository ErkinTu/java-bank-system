package controller;

import model.Client;
import service.PaymentMethod.PaymentMethod;
import service.PaymentServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class PaymentController {
    private final List<PaymentMethod> availableMethods;
    private final PaymentServiceImpl paymentServiceImpl;

    public PaymentController(List<PaymentMethod> services) {
        this.availableMethods = new ArrayList<>(services);
        this.paymentServiceImpl = new PaymentServiceImpl();
    }

    public List<PaymentMethod> getAvailableMethods() {
        return new ArrayList<>(availableMethods);
    }

    public PaymentResult processPayment(Client sender, Client receiver, double amount, PaymentMethod service) {
        PaymentServiceImpl.TransferResult result = paymentServiceImpl.executeTransfer(sender, receiver, amount, service);
        return new PaymentResult(result.isSuccess(), result.getMessage());
    }

    public static class PaymentResult {
        private final boolean success;
        private final String message;

        public PaymentResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}