package controller;

import model.Bank;
import service.interfaces.BankService;

import java.util.List;

public class BankController {
    private final BankService clientService;

    public BankController(BankService clientService) {
        this.clientService = clientService;
    }

    public List<Bank> getBanks() {
        return clientService.getAllBanks();
    }
}