package service.impl;

import model.Bank;
import repository.BankRepository;
import service.interfaces.BankService;

import java.util.List;

public class BankServiceImpl implements BankService {
    private List<Bank> banks;
    private final BankRepository bankRepository;

    public BankServiceImpl(String csvPath) {
        this.bankRepository = new BankRepository(csvPath);
        loadBanks();
    }

    // Getters
    @Override
    public Bank getBankByCode(String code) {
        return banks.stream().filter(bank -> bank.getCode().equals(code)).findFirst().orElse(null);
    }

    @Override
    public List<Bank> getAllBanks() {
        return banks;
    }

    private void loadBanks() {
        banks = bankRepository.loadBanks();
    }

    public void saveBanks() {
        bankRepository.saveBanks(banks);
    }
}