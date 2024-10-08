package ui;
import bean.Chambre;
import service.ChambreService;
import service.ClientService;
import service.HotelService;
import service.PrixDynamiqueService;

import java.util.List;
import java.util.Scanner;

public class ChambreMenu {
    private ChambreService chambreService;
    private ClientService clientService;
    private PrixDynamiqueService prixDynamiqueService;
    private HotelService hotelService;
    private Scanner scanner;
    public ChambreMenu(ChambreService chambreService) {
        this.chambreService = new ChambreService();
        this.scanner = new Scanner(System.in);
        this.clientService = new ClientService();
        this.hotelService = new HotelService();
        this.prixDynamiqueService = new PrixDynamiqueService();
    }
    public void chambreMenu()  {
        while (true) {
            System.out.println("1. Create room");
            System.out.println("2. Update room");
            System.out.println("3. Delete room by id");
            System.out.println("4. Show room by id");
            System.out.println("5. Show all rooms");
            System.out.println("6. Menu Reservation");
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
                    System.out.println("chambre :"+chambre.getNumero()+ ", type :"+chambre.getType()+ ",price :"+chambre.getBasePrice());
                    break;
                case 5:
                    List<Chambre> chambres = chambreService.getAllChambre();
                    for (Chambre chambre1 : chambres) {
                        System.out.println(chambre1);
                    }
                    break;
                case 6:
                    reservationMenu(clientService,chambreService,hotelService,prixDynamiqueService);
                    break;
            }
        }
    }

    public void reservationMenu(ClientService clientService, ChambreService chambreService, HotelService hotelService, PrixDynamiqueService prixDynamiqueService) {
        ReservationMenu reservationMenu = new ReservationMenu(clientService, chambreService,hotelService,prixDynamiqueService);
        reservationMenu.reservationMenu();
    }

}
