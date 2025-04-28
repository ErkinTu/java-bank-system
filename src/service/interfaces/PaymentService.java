package service.interfaces;

import model.Client;
import model.dto.TransferResult;
import model.dto.ValidationResult;
import service.PaymentMethod.PaymentMethod;

public interface PaymentService {
    TransferResult executeTransfer(Client sender, Client receiver, double amount, PaymentMethod paymentMethod);
    ValidationResult validateTransfer(Client sender, Client receiver, double amount);
}
