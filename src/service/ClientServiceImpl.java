package service;

import model.Client;
import service.CSVReader.CSVClientsReader;
import service.CSVWriter.CSVClientsWriter;

import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl {
    private List<Client> clients;
    private final CSVClientsReader csvClientsReader;
    private final CSVClientsWriter csvClientsWriter;
    private final String csvPath;

    public ClientServiceImpl(String csvPath) {
        clients = new ArrayList<>();
        csvClientsReader = new CSVClientsReader();
        csvClientsWriter = new CSVClientsWriter();
        this.csvPath = csvPath;
        loadClients(csvPath);
    }

//    Getters
    public Client getClientByInn(String inn) {
        return clients.stream().filter(client -> client.getInn().equals(inn)).findFirst().orElse(null);
    }

    public List<Client> getAllClients() {
        return clients;
    }

    // Repo*
    public List<Client> loadClients(String fileName) {
        clients = csvClientsReader.read(fileName);
        return clients;
    }

    // Repo*
    public void saveClients(List<Client> clients) {
        csvClientsWriter.save(clients, csvPath);
    }
}
