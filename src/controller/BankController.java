package controller;

import model.Bank;
import service.BankServiceImpl;

import java.util.List;

public class BankController {
    private final BankServiceImpl clientService;

    public BankController(BankServiceImpl clientService) {
        this.clientService = clientService;
    }

    public List<Bank> getBanks() {
        return clientService.getAllBanks();
    }
}