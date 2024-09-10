package service;

import bean.Client;
import repository.impl.ClientDaoImpl;
import java.util.Scanner;

public class ClientService {

    private ClientDaoImpl clientDaoImpl;
    private Scanner scanner;

    public ClientService()  {
        this.clientDaoImpl = new ClientDaoImpl();
        this.scanner = new Scanner(System.in);
    }

    public void saveClient()  {
        System.out.println("Enter Client name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Client age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Client address: ");
        String address = scanner.nextLine();
        System.out.println("Enter Client phone: ");
        String phone = scanner.nextLine();
        Client client = new Client(name, age, address, phone);
        clientDaoImpl.saveClient(client);
    }
    public void updateClient()  {
        System.out.println("Enter Client id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Client name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Client age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Client address: ");
        String address = scanner.nextLine();
        System.out.println("Enter Client phone: ");
        String phone = scanner.nextLine();
        Client client = new Client(name, age, address, phone);
        clientDaoImpl.updateClient(client);
    }
    public void deleteClient()  {
        System.out.println("Enter Client id: ");
        int id = scanner.nextInt();
        clientDaoImpl.deleteClient(id);
    }
    public Client getClientById() {
        System.out.println("Enter Client id: ");
        int id = scanner.nextInt();
        return clientDaoImpl.getClientById(id);
    }


}
