package service.PaymentMethod;

import model.Client;

public class PhonePaymentMethod implements PaymentMethod {
    @Override
    public boolean transfer(Client from, Client to, double amount) {
        if (!canTransferTo(to)) {
            System.err.println("Ошибка: у получателя отсутствует номер телефона.");
            return false;
        }

        from.sendMoney(amount);
        to.receiveMoney(amount);

        System.out.println("Перевод по номеру телефона выполнен: " + amount +
                " от " + from.getFirstName() + " " + from.getLastName() +
                " к " + to.getFirstName() + " " + to.getLastName());
        return true;
    }

    @Override
    public boolean canTransferTo(Client receiver) {
        return receiver != null && receiver.getPhoneNumber() != null &&
                !receiver.getPhoneNumber().isEmpty() && !receiver.getPhoneNumber().equals("null");
    }

    @Override
    public String getDescription() {
        return "Перевод по номеру телефона";
    }
}