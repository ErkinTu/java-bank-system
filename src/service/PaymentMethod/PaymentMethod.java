package service.PaymentMethod;

import model.Client;

public interface PaymentMethod {
    boolean transfer(Client from, Client to, double amount);
//    Проверяет наличие нужных реквизитов
    boolean canTransferTo(Client receiver);
    String getDescription();
}