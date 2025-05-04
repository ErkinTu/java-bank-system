package repository;

import model.Client;
import repository.CSVReader.CSVClientsReader;
import repository.CSVWriter.CSVClientsWriter;

import java.util.List;

public class ClientRepository {
    private final CSVClientsReader csvClientsReader;
    private final CSVClientsWriter csvClientsWriter;
    private final String csvPath;

    public ClientRepository(String csvPath) {
        this.csvClientsReader = new CSVClientsReader();
        this.csvClientsWriter = new CSVClientsWriter();
        this.csvPath = csvPath;
    }

    public List<Client> loadClients() {
        return csvClientsReader.read(csvPath);
    }

    public void saveClients(List<Client> clients) {
        csvClientsWriter.save(clients, csvPath);
    }
}