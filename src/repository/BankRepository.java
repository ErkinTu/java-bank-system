package repository;

import model.Bank;
import repository.CSVReader.CSVBanksReader;
import repository.CSVWriter.CSVBanksWriter;

import java.util.List;

public class BankRepository {
    private final CSVBanksReader csvBanksReader;
    private final CSVBanksWriter csvBanksWriter;
    private final String csvPath;

    public BankRepository(String csvPath) {
        this.csvBanksReader = new CSVBanksReader();
        this.csvBanksWriter = new CSVBanksWriter();
        this.csvPath = csvPath;
    }

    public List<Bank> loadBanks() {
        return csvBanksReader.read(csvPath);
    }

    public void saveBanks(List<Bank> banks) {
        csvBanksWriter.save(banks, csvPath);
    }
}