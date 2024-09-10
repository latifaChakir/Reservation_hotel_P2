package ui;
import service.ChambreService;
import service.ClientService;
import service.HotelService;
import service.ReservationService;

import java.util.Scanner;

public class ReservationMenu {
    private Scanner scanner;
    private ClientService clientService;
    private ChambreService chambreService;
    private ClientMenu clientMenu;
    private ChambreMenu chambreMenu;
    private HotelMenu hotelMenu;
    private HotelService hotelService;
    private ReservationService reservationService;
    public ReservationMenu(ClientService clientService, ChambreService chambreService, HotelService hotelService) {
        this.scanner = new Scanner(System.in);
        this.clientService = clientService;
        this.chambreService = chambreService;
        this.clientMenu = new ClientMenu(clientService);
        this.chambreMenu = new ChambreMenu(chambreService);
        this.hotelService = new HotelService();
        this.hotelMenu=new HotelMenu(hotelService);
        this.reservationService = new ReservationService();
       ;
    }
    public void reservationMenu()  {
        while (true) {
            System.out.println("1. Create Reservation");
            System.out.println("2. Show All Reservations");
            System.out.println("3. Update Reservation By Id");
            System.out.println("4. Show Reservation By Id");
            System.out.println("5. Delete Reservation By Id");
            System.out.println("6. Clients Menu");
            System.out.println("7. Rooms Menu");
            System.out.println("8. Hotel Menu");
            System.out.println("9. EXIT");
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
                    reservationService.saveReservation();
                    break;
                case 2:reservationService.updateReservation();
                    break;
                case 3:reservationService.deleteReservation();
                    break;
                case 4:reservationService.getReservationById();
                     break;
                case 5:
                    reservationService.getAllReservations();
                    break;
                case 6:
                    clientMenu.clientMenu();
                    break;
                case 7:
                    chambreMenu.chambreMenu();
                    break;
                case 8:
                    hotelMenu.hotelMenu();
                    break;
                case 9:
                    System.out.println("Goodbye!");
                    break;

            }
        }
    }

}
