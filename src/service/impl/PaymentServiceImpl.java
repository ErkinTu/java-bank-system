package service.impl;

import model.Client;
import model.dto.TransferResult;
import model.dto.ValidationResult;
import service.PaymentMethod.PaymentMethod;
import service.interfaces.PaymentService;

public class PaymentServiceImpl implements PaymentService {
    @Override
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

    @Override
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
}