package ui;

import bean.Client;
import service.ChambreService;
import service.ClientService;

import java.util.Scanner;

public class ReservationMenu {
    private Scanner scanner;
    private ClientService clientService;
    private ChambreService chambreService;
    private ClientMenu clientMenu;
    private ChambreMenu chambreMenu;
    public ReservationMenu(ClientService clientService, ChambreService chambreService) {
        this.scanner = new Scanner(System.in);
        this.clientService = clientService;
        this.chambreService = chambreService;
        this.clientMenu = new ClientMenu(clientService);
        this.chambreMenu = new ChambreMenu(chambreService);
       ;
    }
    public void reservationMenu()  {
        while (true) {
            System.out.println("1. Create Reservation");
            System.out.println("2. Show All Reservations");
            System.out.println("3. Show Reservation By Id");
            System.out.println("4. Update Reservation By Id");
            System.out.println("5. Delete Reservation By Id");
            System.out.println("6. Deleted Reservations");
            System.out.println("7. Clients Menu");
            System.out.println("8. Rooms Menu");
            System.out.println("9. Exit");
            System.out.println("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                     break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    break;
                case 7:
                    clientMenu.clientMenu();
                    break;
                case 8:
                    chambreMenu.chambreMenu();
                    break;
            }
        }
    }

}
