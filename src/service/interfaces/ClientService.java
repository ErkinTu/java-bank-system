package service.interfaces;

import model.Client;

import java.util.List;

public interface ClientService {
    Client getClientByInn(String inn);
    List<Client> getAllClients();
    void saveClients(List<Client> clients);
}
