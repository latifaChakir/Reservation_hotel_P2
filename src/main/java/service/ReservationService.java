package service;

import bean.Chambre;
import bean.Client;
import bean.Reservation;
import Dao.impl.ChambreDaoImpl;
import Dao.impl.ClientDaoImpl;
import Dao.impl.ReservationDaoImpl;
import enums.ReservationStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
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

    public Chambre trouverChambre(int ChambreId, LocalDate dateDebut, LocalDate dateFin) {
        List<Chambre> chambres = chambreDaoImpl.getAllChambres();
        List<Reservation> reservations = reservationDaoImpl.getAllReservations();
        for (Chambre chambre : chambres) {
            if (chambre.getId() == ChambreId) {
                boolean isAvailable = true;

                for (Reservation reservation : reservations) {
                    if (reservation.getChambre().getId() == ChambreId) {
                        if (!(dateFin.isBefore(reservation.getDateDebut()) || dateDebut.isAfter(reservation.getDateFin()))) {
                            System.out.println("La chambre " + ChambreId + " est déjà réservée du " +
                                    reservation.getDateDebut() + " au " + reservation.getDateFin());
                            isAvailable = false;
                            break;
                        }

                    }
                }
                if (isAvailable) {
                    return chambre;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public Reservation saveReservation() {
        System.out.println("Enter the client id: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the room id: ");
        int roomId = scanner.nextInt();
        scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        System.out.print("Enter the start date (yyyy-MM-dd): ");
        LocalDate startDateParse;
        while (true) {
            try {
                startDateParse = LocalDate.parse(scanner.nextLine(), formatter);
                if (startDateParse.isBefore(now)) {
                    System.out.println("Start date must be today or later. Please enter a valid start date.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid start date. Please enter the date in the format yyyy-MM-dd.");
            }
        }

        System.out.print("Enter the end date (yyyy-MM-dd): ");
        LocalDate endDateParse;
        while (true) {
            try {
                endDateParse = LocalDate.parse(scanner.nextLine(), formatter);
                if (endDateParse.isBefore(startDateParse)) {
                    System.out.println("End date cannot be before the start date. Please enter a valid end date.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid end date. Please enter the date in the format yyyy-MM-dd.");
            }
        }

        Chambre chambreChoisie = null;
        Client clientChoisi = null;

        try {
            clientChoisi = clientDaoImpl.getClientById(clientId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        chambreChoisie = trouverChambre(roomId, startDateParse, endDateParse);
        if (chambreChoisie == null) {
            System.out.println("Room is not available for the selected dates.");
            return null;
        }

        ReservationStatus status = ReservationStatus.RESERVED;
        Reservation reservation = new Reservation(clientChoisi, chambreChoisie, startDateParse, endDateParse, status);
        return reservationDaoImpl.saveReservation(reservation);
    }

    public void updateReservation() {
        System.out.println("Enter the reservation id: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the client id: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();

        Client fetchedClient = null;
        try {
            fetchedClient = clientDaoImpl.getClientById(clientId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Enter the room number: ");
        int roomId = scanner.nextInt();
        scanner.nextLine();

        // Format pour la saisie des dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        // Saisie et validation de la date de début
        System.out.print("Enter the start date (yyyy-MM-dd): ");
        LocalDate startDateParse;
        while (true) {
            try {
                startDateParse = LocalDate.parse(scanner.nextLine(), formatter);
                if (startDateParse.isBefore(now)) {
                    System.out.println("Start date must be today or later. Please enter a valid start date.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid start date. Please enter the date in the format yyyy-MM-dd.");
            }
        }

        // Saisie et validation de la date de fin
        System.out.print("Enter the end date (yyyy-MM-dd): ");
        LocalDate endDateParse;
        while (true) {
            try {
                endDateParse = LocalDate.parse(scanner.nextLine(), formatter);
                if (endDateParse.isBefore(startDateParse)) {
                    System.out.println("End date cannot be before the start date. Please enter a valid end date.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid end date. Please enter the date in the format yyyy-MM-dd.");
            }
        }

        Chambre fetchedChambre = trouverChambre(roomId, startDateParse, endDateParse);
        if (fetchedChambre == null) {
            System.out.println("Room is not available for the selected dates.");
            return;
        }

        ReservationStatus status = ReservationStatus.RESERVED;
        Reservation reservation = new Reservation(fetchedClient, fetchedChambre, startDateParse, endDateParse, status);
        reservation.setId(reservationId);
        reservationDaoImpl.updateReservation(reservation);
    }

    public void cancelReservation() {
        System.out.println("Enter the reservation id: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();
        reservationDaoImpl.cancelReservation(reservationId);
    }

    public Reservation getReservationById() {
        System.out.println("Enter the reservation id: ");
        int reservationId = scanner.nextInt();
        return reservationDaoImpl.getReservationById(reservationId);
    }
    public List<Reservation> getAllReservations(){
        return reservationDaoImpl.getAllReservations();

    }
}
