package service.PaymentMethod;

import model.Client;

public class BankAccountPaymentMethod implements PaymentMethod {
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

//      Validate BankAccount
        if (to.getBankAccount() == null || to.getBankAccount().isEmpty()) {
            System.err.println("Ошибка: у получателя отсутствует банковский счет.");
            return false;
        }

//      Transfer successfully
        from.sendMoney(amount);
        to.receiveMoney(amount);

        System.out.println("Перевод по банковскому счету выполнен: " + amount +
                " от " + from.getFirstName() + " " + from.getLastName() +
                " к " + to.getFirstName() + " " + to.getLastName());
        return true;
    }

    @Override
    public String getDescription() {
        return "Перевод по банковскому счету";
    }
}
