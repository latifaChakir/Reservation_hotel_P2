package service;

import bean.Chambre;
import bean.RoomType;
import Dao.impl.ChambreDaoImpl;

import java.util.Scanner;

public class ChambreService {

    private ChambreDaoImpl chambreDaoImpl;
    private Scanner scanner;
    public ChambreService() {
        this.chambreDaoImpl = new ChambreDaoImpl();
        this.scanner = new Scanner(System.in);
    }

    public void saveChambre() {
        System.out.println("enter the number of the room");
        int chambreNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the type of the room (e.g., SINGLE, DOUBLE, SUITE):");
        String chambreTypeStr = scanner.nextLine();
        RoomType chambreType = RoomType.valueOf(chambreTypeStr.toUpperCase());
        boolean isDisponible=true;
        Chambre chambre = new Chambre(chambreNumber,chambreType,isDisponible);
        chambreDaoImpl.saveChambre(chambre);
    }
    public Chambre updateChambre() {
        System.out.println("Enter the ID of the room:");
        int chambreId = scanner.nextInt();
        scanner.nextLine();

        Chambre fetchChambre = chambreDaoImpl.getChambreById(chambreId);
        if (fetchChambre == null) {
            System.out.println("No room found with ID: " + chambreId);
            return null;
        }
        System.out.println("Current room details:");
        System.out.println(fetchChambre);

        System.out.println("Enter the new number of the room:");
        int chambreNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the new type of the room (e.g., SINGLE, DOUBLE, SUITE):");
        String chambreTypeStr = scanner.nextLine();
        RoomType chambreType;
        try {
            chambreType = RoomType.valueOf(chambreTypeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid room type entered.");
            return null;
        }
        System.out.println("Is the room available (true/false)?");
        boolean isDisponible = scanner.nextBoolean();
        Chambre updatedChambre = new Chambre(chambreNumber, chambreType, isDisponible);
        updatedChambre.setId(chambreId);

        chambreDaoImpl.updateChambre(updatedChambre);
        return updatedChambre;
    }
    public void deleteChambre()  {
        System.out.println("Enter room id: ");
        int id = scanner.nextInt();
        chambreDaoImpl.deleteChambre(id);
    }
    public Chambre getChambreById() {
        System.out.println("Enter room id: ");
        int id = scanner.nextInt();
        return chambreDaoImpl.getChambreById(id);
    }


}
