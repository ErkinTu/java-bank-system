package service.PaymentMethod;

import model.Client;

public interface PaymentMethod {
    boolean transfer(Client from, Client to, double amount);
    String getDescription();
}