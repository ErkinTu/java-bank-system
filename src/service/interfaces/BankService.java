package service.interfaces;

import model.Bank;

import java.util.List;

public interface BankService {
    Bank getBankByCode(String code);  // Получить банк по коду
    List<Bank> getAllBanks();
}
