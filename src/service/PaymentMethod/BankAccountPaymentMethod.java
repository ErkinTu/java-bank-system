package service.PaymentMethod;

import model.Client;

public class BankAccountPaymentMethod implements PaymentMethod {
    @Override
    public boolean transfer(Client from, Client to, double amount) {
        if (!canTransferTo(to)) {
            System.err.println("Ошибка: у получателя отсутствует банковский счет.");
            return false;
        }

        from.sendMoney(amount);
        to.receiveMoney(amount);

        System.out.println("Перевод по банковскому счету выполнен: " + amount +
                " от " + from.getFirstName() + " " + from.getLastName() +
                " к " + to.getFirstName() + " " + to.getLastName());
        return true;
    }

    @Override
    public boolean canTransferTo(Client receiver) {
        return receiver.getBankAccount() != null && !receiver.getBankAccount().isEmpty();
    }

    @Override
    public String getDescription() {
        return "Перевод по банковскому счету";
    }
}