package main;

import model.Client;
import service.CSVReader.CSVClientsReader;
import service.CSVWriter.CSVClientsWriter;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        CSVClientsReader clientsReader = new CSVClientsReader();
        List<Client> clients = clientsReader.read("data/clients.csv");

        Client client1 = clients.get(0);
        System.out.println(client1.getBalance());
        client1.receiveMoney(49499.9);

        CSVClientsWriter clientsWriter = new CSVClientsWriter();
        clientsWriter.save(clients, "data/clients-tu.csv");

        List<Client> clientst = clientsReader.read("data/clients-tu.csv");
        Client client1t = clientst.get(0);
        System.out.println(client1t.getBalance());
    }
}
