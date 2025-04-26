package service.PaymentMethod;

import model.Client;

import java.util.Objects;

public class PhonePaymentMethod implements PaymentMethod {
    @Override
    public boolean transfer(Client from, Client to, double amount) {
        if (!canTransferTo(from)) {
            System.err.println("Ошибка: у отправителя " + from.getFirstName() + " отсутствует номер телефона.");
            return false;
        } else if (!canTransferTo(to)) {
            System.err.println("Ошибка: у получателя " + to.getFirstName() + " отсутствует номер телефона.");
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
        return !Objects.equals(receiver.getPhoneNumber(), "null");
    }

    @Override
    public String getDescription() {
        return "Перевод по номеру телефона";
    }
}