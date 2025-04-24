package service.CSVWriter;

import model.Client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVClientsWriter implements CSVWriter<Client> {
    @Override
    public void save(List<Client> clients, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("firstName; secondName; dateOfBirth; inn; phoneNumber; bankAccount; balance");
            bw.newLine();
            for (Client client : clients) {
                String csvLine = String.join("; ",
                        client.getFirstName(),
                        client.getLastName(),
                        client.getDateOfBirth(),
                        client.getInn(),
                        client.getPhoneNumber(),
                        client.getBankAccount(),
                        Double.toString(client.getBalance())
                );
                bw.write(csvLine);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
