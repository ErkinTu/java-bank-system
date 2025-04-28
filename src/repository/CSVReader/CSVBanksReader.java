package repository.CSVReader;

import model.Bank;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

public class CSVBanksReader implements CSVReader<Bank>{

    @Override
    public List<Bank> read(String filename) {
        List<Bank> banks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(";");

                if (values.length != 4) continue;

                String name = values[0].trim();
                String code = values[1].trim();
                String address = values[2].trim();
                double balance = parseDouble(values[3].trim());

                Bank bank = new Bank(name, code, address, balance);
                banks.add(bank);
            }

        } catch (IOException e) {
            System.err.println("Ошибка при чтении CSV файла: " + e.getMessage());
        }

        return banks;
    }
}
