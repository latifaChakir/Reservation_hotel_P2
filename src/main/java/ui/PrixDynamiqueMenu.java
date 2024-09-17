package ui;

import bean.PrixDynamique;
import enums.Saison;
import enums.Days;
import enums.Events;
import service.ChambreService;
import service.ClientService;
import service.HotelService;
import service.PrixDynamiqueService;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PrixDynamiqueMenu {
    private HotelService hotelService;
    private PrixDynamiqueService prixDynamiqueService;
    private ClientService clientService;
    private Scanner scanner;
    private ChambreService chambreService;

    public PrixDynamiqueMenu(PrixDynamiqueService prixDynamiqueService) {
        scanner = new Scanner(System.in);
        this.prixDynamiqueService = new PrixDynamiqueService();
        hotelService = new HotelService();
        clientService = new ClientService();
        chambreService = new ChambreService();
    }

    public void prixDynamiqueMenu() {
        while (true) {
            System.out.println("1. Add new pricing rule");
            System.out.println("2. Update existing pricing rule");
            System.out.println("3. Delete pricing rule");
            System.out.println("4. Display all pricing rules");
            System.out.println("5. Reservations Menu");
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
                    addPricingRule();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    displayAllPricingRules();
                    break;
                case 5:
                    reservationMenu(clientService, chambreService, hotelService);
                    return; // Exit the menu
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void addPricingRule() {
        try {
            System.out.println("Enter season (FALL, SUMMER, etc.):");
            Saison season = parseEnum(scanner.nextLine(), Saison.class);

            System.out.println("Enter day of the week (MONDAY, TUESDAY, SATURDAY, etc.):");
            Days dayOfWeek = parseEnum(scanner.nextLine(), Days.class);

            System.out.println("Enter event (FESTIVAL, HOLIDAY, NEW_YEAR, etc.):");
            Events event = parseEnum(scanner.nextLine(), Events.class);

            BigDecimal multiplier = null;
            while (multiplier == null) {
                try {
                    System.out.println("Enter multiplier or coefficient (use '.' as decimal separator):");
                    multiplier = scanner.nextBigDecimal();
                    scanner.nextLine();  // Clear the buffer after reading BigDecimal
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid decimal number.");
                    scanner.next();  // Clear the invalid input from the scanner
                }
            }

            PrixDynamique prixDynamique = new PrixDynamique(season, dayOfWeek, event, multiplier);
            prixDynamiqueService.addPricingRule(prixDynamique);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
//    private void updatePricingRule() {
//        // Display all pricing rules
//        displayAllPricingRules();
//
//        System.out.println("Enter the ID of the pricing rule to update:");
//        int id = Integer.parseInt(scanner.nextLine());
//
//        // Get updated details
//        System.out.println("Enter new base price:");
//        BigDecimal basePrice = scanner.nextBigDecimal();
//        scanner.nextLine(); // consume newline
//
//        // Get updated details for dynamic price calculation
//        System.out.println("Enter new season (FALL, SUMMER, etc.):");
//        Saison season = parseEnum(scanner.nextLine(), Saison.class);
//        System.out.println("Enter new day of the week (MONDAY, TUESDAY, SATURDAY, etc.):");
//        Days dayOfWeek = parseEnum(scanner.nextLine(), Days.class);
//        System.out.println("Enter new event (FESTIVAL, HOLIDAY, NEW_YEAR, etc.):");
//        Events event = parseEnum(scanner.nextLine(), Events.class);
//
//        // Create and update the pricing rule
//        PrixDynamique prixDynamique = new PrixDynamique(season, dayOfWeek, event, basePrice);
//        prixDynamiqueService.updatePricingRule(prixDynamique);
//
//        System.out.println("Pricing rule updated successfully.");
//    }

//    private void deletePricingRule() {
//        // Display all pricing rules
//        displayAllPricingRules();
//
//        System.out.println("Enter the ID of the pricing rule to delete:");
//        int id = Integer.parseInt(scanner.nextLine());
//
//        prixDynamiqueService.deletePricingRule(id);
//
//        System.out.println("Pricing rule deleted successfully.");
//    }

    private void displayAllPricingRules() {
        List<PrixDynamique> pricingRules = prixDynamiqueService.getAllPricingRules();
        for (PrixDynamique rule : pricingRules) {
            System.out.println(rule);
        }
    }

    private <E extends Enum<E>> E parseEnum(String value, Class<E> enumClass) {
        try {
            return Enum.valueOf(enumClass, value.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid value for enum " + enumClass.getSimpleName() + ". Please try again.");
            return null;
        }
    }

    public void reservationMenu(ClientService clientService, ChambreService chambreService, HotelService hotelService) {
        ReservationMenu reservationMenu = new ReservationMenu(clientService, chambreService, hotelService,prixDynamiqueService);
        reservationMenu.reservationMenu();
    }
}
