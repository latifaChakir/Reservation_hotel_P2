package ui;

import bean.Client;
import bean.Hotel;
import service.ChambreService;
import service.ClientService;
import service.HotelService;

import java.util.Scanner;

public class HotelMenu {
    private HotelService hotelService;
    private ClientService clientService;
    private Scanner scanner;
    private ChambreService chambreService;
    public HotelMenu(HotelService hotelService) {
        this.hotelService = new HotelService();
        this.scanner = new Scanner(System.in);
        this.chambreService = new ChambreService();
        this.clientService = new ClientService();
    }
    public void hotelMenu() {
        while (true) {
            System.out.println("1. Create HOTEL");
            System.out.println("2. Update HOTEL");
            System.out.println("3. Delete HOTEL by id");
            System.out.println("4. Show HOTEL by id");
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
                    hotelService.saveHotel();
                    break;
                case 2:
                    hotelService.updateHotel();
                    break;
                case 3:
                    hotelService.deleteHotel();
                    break;
                case 4:
                    Hotel hotel = hotelService.getHotelByID();
                    System.out.println("hotel name :"+hotel.getName()+ ", address :"+hotel.getAddress());
                    break;
                case 5:
                    reservationMenu(clientService,chambreService,hotelService);
                    break;
            }
        }
    }
    public void reservationMenu(ClientService clientService, ChambreService chambreService, HotelService hotelService) {
        ReservationMenu reservationMenu = new ReservationMenu(clientService, chambreService, hotelService);
        reservationMenu.reservationMenu();
    }
}
