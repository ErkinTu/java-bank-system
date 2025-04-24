package service.CSVWriter;

import model.Bank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVBanksWriter implements CSVWriter<Bank> {
    @Override
    public void save(List<Bank> banks, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("name; code; address; balance");
            bw.newLine();
            for (Bank bank : banks) {
                String csvLine = String.join("; ",
                        bank.getName(),
                        bank.getCode(),
                        bank.getAddress(),
                        Double.toString(bank.getBalance())
                );
                bw.write(csvLine);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
