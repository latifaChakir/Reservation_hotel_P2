package ui;

import bean.Client;
import service.ChambreService;
import service.ClientService;

import java.sql.SQLException;
import java.util.Scanner;

public class ClientMenu {
    private ClientService clientService;
    private Scanner scanner;
    private ChambreService chambreService;

    public ClientMenu(ClientService clientService) {
        this.clientService = clientService;
        this.scanner = new Scanner(System.in);
        this.chambreService = new ChambreService();
    }
    public void clientMenu()  {
        while (true) {
            System.out.println("1. Create Client");
            System.out.println("2. Update Client");
            System.out.println("3. Delete Client by id");
            System.out.println("4. Show Client by id");
            System.out.println("5. Reservation Menu");
            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                   clientService.saveClient();
                   break;
                case 2:
                    clientService.updateClient();
                    break;
                case 3:
                    clientService.deleteClient();
                    break;
                case 4:
                    Client client = clientService.getClientById();
                    System.out.println("client :"+client.getName()+ ", age :"+client.getAge()+", address :"+client.getAddress());
                    break;
                case 5:
                    reservationMenu(clientService,chambreService);
                    break;
            }
        }
    }

    public void reservationMenu(ClientService clientService, ChambreService chambreService) {
        ReservationMenu reservationMenu = new ReservationMenu(clientService, chambreService);
        reservationMenu.reservationMenu();
    }
}

