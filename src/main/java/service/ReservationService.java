package service;

import bean.Chambre;
import bean.Client;
import bean.Reservation;
import repository.impl.ChambreDaoImpl;
import repository.impl.ClientDaoImpl;
import repository.impl.ReservationDaoImpl;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class ReservationService {
    private ReservationDaoImpl reservationDaoImpl;
    private Scanner scanner;
    private ClientDaoImpl clientDaoImpl;
    private ChambreDaoImpl chambreDaoImpl;
    public ReservationService() {
        reservationDaoImpl = new ReservationDaoImpl();
        scanner = new Scanner(System.in);
        clientDaoImpl = new ClientDaoImpl();
        chambreDaoImpl = new ChambreDaoImpl();
    }

    public Reservation saveReservation() {
        System.out.println("Enter the client id: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the room id: ");
        int roomId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the start date: ");
        String startdate = scanner.nextLine();
        System.out.println("Enter the end date: ");
        String endDate = scanner.nextLine();
        LocalDate endDateParse = LocalDate.parse(endDate);
        LocalDate startDateParse = LocalDate.parse(startdate);
        Chambre chambre_choisis=null;
        Client client_choisis = null;

        try {
            chambre_choisis=chambreDaoImpl.getChambreById(roomId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        try {
            client_choisis=clientDaoImpl.getClientById(clientId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Reservation reservation=new Reservation(client_choisis,chambre_choisis,startDateParse,endDateParse);
        return reservationDaoImpl.saveReservation(reservation);
    }
}
