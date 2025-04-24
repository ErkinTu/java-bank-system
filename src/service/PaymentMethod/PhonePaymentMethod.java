package service.PaymentMethod;

import model.Client;

public class PhonePaymentMethod implements PaymentMethod {
    @Override
    public boolean transfer(Client from, Client to, double amount) {
//        Validate
        if (amount <= 0) {
            System.err.println("Ошибка: сумма перевода должна быть положительной.");
            return false;
        }

        if (from.getBalance() < amount) {
            System.err.println("Ошибка: недостаточно средств у отправителя.");
            return false;
        }

//        Validate Telephone
        if (to.getPhoneNumber() == null || to.getPhoneNumber().isEmpty()) {
            System.err.println("Ошибка: у получателя отсутствует номер телефона.");
            return false;
        }

//        Transfer successfully
        from.sendMoney(amount);
        to.receiveMoney(amount);

        System.out.println("Перевод по номеру телефона выполнен: " + amount +
                " от " + from.getFirstName() + " " + from.getLastName() +
                " к " + to.getFirstName() + " " + to.getLastName());
        return true;
    }

    @Override
    public String getDescription() {
        return "Перевод по номеру телефона";
    }
}
