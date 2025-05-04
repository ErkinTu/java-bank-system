package service.PaymentMethod;

import model.Client;


public class BankAccountCashbackPaymentMethod extends BankAccountPaymentMethod {

    private static final double CASHBACK_PERCENT = 0.01; // 1% кэшбэк

    @Override
    public boolean transfer(Client from, Client to, double amount) {
        if (!super.transfer(from, to, amount)) {
            return false;
        }

        if (isBankEligibleForCashback(from)) {
            double cashback = amount * CASHBACK_PERCENT;
            from.receiveMoney(cashback);
            System.out.println("Начислен кэшбэк " + cashback + " клиенту " +
                    from.getFirstName() + " " + from.getLastName());
        } else {
            System.out.println("Банк клиента " + from.getFirstName() + " не участвует в программе кэшбэка.");
        }

        return true;
    }

    private boolean isBankEligibleForCashback(Client client) {
        String bankName = client.getBankAccount();
        return bankName != null && !bankName.equals("BadBank");
    }

    @Override
    public String getDescription() {
        return "Перевод с кэшбэком по банковскому счету";
    }
}
