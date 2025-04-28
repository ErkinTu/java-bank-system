package service.impl;

import model.Bank;
import repository.CSVReader.CSVBanksReader;
import repository.CSVWriter.CSVBanksWriter;
import service.interfaces.BankService;

import java.util.ArrayList;
import java.util.List;


public class BankServiceImpl implements BankService {
    private List<Bank> banks;
    private final CSVBanksReader csvBanksReader;
    private final CSVBanksWriter csvBanksWriter;
    private final String csvPath;

    public BankServiceImpl(String csvPath) {
        banks = new ArrayList<>();
        csvBanksReader = new CSVBanksReader();
        csvBanksWriter = new CSVBanksWriter();
        this.csvPath = csvPath;
        loadBanks(csvPath);
    }

//    Getters
    @Override
    public Bank getBankByCode(String code) {
        return banks.stream().filter(bank -> bank.getCode().equals(code)).findFirst().orElse(null);
    }

    @Override
    public List<Bank> getAllBanks() {
        return banks;
    }

    // Repo*
    public List<Bank> loadBanks(String fileName) {
        banks = csvBanksReader.read(fileName);
        return banks;
    }

    // Repo*
    public void saveBanks(List<Bank> banks) {
        csvBanksWriter.save(banks, csvPath);
    }
}
