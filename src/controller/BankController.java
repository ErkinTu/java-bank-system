package controller;

import model.Bank;
import service.CSVReader.CSVBanksReader;

import java.util.List;

public class BankController {
    private List<Bank> banks;
    private static final String CSV_PATH = "data/banks.csv";

//    Вызов через экземпляр класса, а не через сам класс.
//    public void loadBanks() {
//        banks = CSVBanksReader.read(CSV_PATH);
//    }
}
