package ui;

import bean.Chambre;
import bean.Client;
import service.ChambreService;
import service.ClientService;
import service.HotelService;

import java.util.Scanner;

public class ChambreMenu {
    private ChambreService chambreService;
    private ClientService clientService;
    private HotelService hotelService;
    private Scanner scanner;
    public ChambreMenu(ChambreService chambreService) {
        this.chambreService = new ChambreService();
        this.scanner = new Scanner(System.in);
        this.clientService = new ClientService();
        this.hotelService = new HotelService();
    }
    public void chambreMenu()  {
        while (true) {
            System.out.println("1. Create room");
            System.out.println("2. Update room");
            System.out.println("3. Delete room by id");
            System.out.println("4. Show room by id");
            System.out.println("5. Menu Reservation");
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
                    chambreService.saveChambre();
                    break;
                case 2:
                    chambreService.updateChambre();
                    break;
                case 3:
                    chambreService.deleteChambre();
                    break;
                case 4:
                    Chambre chambre = chambreService.getChambreById();
                    System.out.println("chambre :"+chambre.getNumero()+ ", type :"+chambre.getType());
                    break;
                case 5:
                    reservationMenu(clientService,chambreService,hotelService);
                    break;
            }
        }
    }

    public void reservationMenu(ClientService clientService, ChambreService chambreService, HotelService hotelService) {
        ReservationMenu reservationMenu = new ReservationMenu(clientService, chambreService,hotelService);
        reservationMenu.reservationMenu();
    }

}
