package ui;

import bean.Hotel;
import service.ChambreService;
import service.ClientService;
import service.HotelService;
import service.StatisticService;

import java.util.Scanner;

public class StatisticMenu {
    private StatisticService statisticService;
    private HotelService hotelService;
    private ClientService clientService;
    private Scanner scanner;
    private ChambreService chambreService;
    public StatisticMenu(StatisticService statisticService) {
        this.statisticService = statisticService;
        scanner = new Scanner(System.in);
        chambreService = new ChambreService();
        hotelService = new HotelService();
        clientService = new ClientService();
    }

    public void staticsMenu() {
        while (true) {
            System.out.println("1. confirmed reservations");
            System.out.println("2. cancel reservations");
            System.out.println("4.  Statistics");
            System.out.println("5.Reservation Menu");
            System.out.print("your choice: ");
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
