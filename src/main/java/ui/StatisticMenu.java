package ui;

import bean.Hotel;
import service.*;

import java.util.Scanner;

public class StatisticMenu {
    private StatisticMenu statisticMenu;
    private StatisticService statisticService;
    private HotelService hotelService;
    private ClientService clientService;
    private Scanner scanner;
    private ChambreService chambreService;
    private PrixDynamiqueService prixDynamiqueService;
    public StatisticMenu(StatisticService statisticService) {
        this.statisticService = new StatisticService();
        scanner = new Scanner(System.in);
        chambreService = new ChambreService();
        prixDynamiqueService = new PrixDynamiqueService();
        hotelService = new HotelService();
        clientService = new ClientService();

    }

    public void staticsMenu() {
        while (true) {
            System.out.println("1. confirmed reservations");
            System.out.println("2. cancel reservations");
            System.out.println("3.  Taux d' occupation");
            System.out.println("4.Reservation Menu");
            System.out.print("your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:statisticService.reservationReservedCount();
                    break;
                case 2:statisticService.reservationCancelledCount();
                    break;
                case 3:statisticService.calculTauxOccupation();
                    break;
                case 4:reservationMenu(clientService,chambreService,hotelService,prixDynamiqueService);
                    break;

            }
        }
    }
    public void reservationMenu(ClientService clientService, ChambreService chambreService, HotelService hotelService, PrixDynamiqueService prixDynamiqueService) {
        ReservationMenu reservationMenu = new ReservationMenu(clientService, chambreService, hotelService, prixDynamiqueService);
        reservationMenu.reservationMenu();
    }

}
