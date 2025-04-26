package service;

import model.Client;
import service.PaymentMethod.PaymentMethod;

public class PaymentServiceImpl {
    public ValidationResult validateTransfer(Client sender, Client receiver, double amount) {
        if (sender == null) {
            return new ValidationResult(false, "Отправитель не выбран");
        }

        if (receiver == null) {
            return new ValidationResult(false, "Получатель не выбран");
        }

        if (sender == receiver) {
            return new ValidationResult(false, "Отправитель и получатель не могут быть одним лицом");
        }

        if (amount <= 0) {
            return new ValidationResult(false, "Сумма должна быть положительной");
        }

        if (sender.getBalance() < amount) {
            return new ValidationResult(false, "Недостаточно средств для перевода");
        }

        return new ValidationResult(true, "Валидация пройдена успешно");
    }

    public TransferResult executeTransfer(Client sender, Client receiver, double amount, PaymentMethod paymentMethod) {
        ValidationResult validationResult = validateTransfer(sender, receiver, amount);
        if (!validationResult.isValid()) {
            return new TransferResult(false, validationResult.getMessage());
        }

        boolean success = paymentMethod.transfer(sender, receiver, amount);

        if (success) {
            return new TransferResult(true, "Перевод успешно выполнен на сумму " + amount);
        } else {
            return new TransferResult(false, "Ошибка при выполнении перевода");
        }
    }

    public static class ValidationResult {
        private final boolean valid;
        private final String message;

        public ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }

        public boolean isValid() {
            return valid;
        }

        public String getMessage() {
            return message;
        }
    }

    public static class TransferResult {
        private final boolean success;
        private final String message;

        public TransferResult(boolean success, String message) {
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