package controller;

import model.Client;
import service.interfaces.ClientService;

import java.util.List;

public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public List<Client> getClientList() {
        return clientService.getAllClients();
    }

    public void saveClientList(List<Client> clientList) {
        clientService.saveClients(clientList);
    }

    public Client getClient(String inn) {
        return clientService.getClientByInn(inn);
    }
}