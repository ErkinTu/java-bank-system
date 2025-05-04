package service.impl;

import model.Client;
import repository.ClientRepository;
import service.interfaces.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    private List<Client> clients;
    private final ClientRepository clientRepository;

    public ClientServiceImpl(String csvPath) {
        this.clientRepository = new ClientRepository(csvPath);
        loadClients();
    }

    // Getters
    @Override
    public Client getClientByInn(String inn) {
        return clients.stream().filter(client -> client.getInn().equals(inn)).findFirst().orElse(null);
    }

    @Override
    public List<Client> getAllClients() {
        return clients;
    }

    private void loadClients() {
        clients = clientRepository.loadClients();
    }

    @Override
    public void saveClients(List<Client> clients) {
        clientRepository.saveClients(clients);
    }
}