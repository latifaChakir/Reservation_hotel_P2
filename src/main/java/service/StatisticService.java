package service;

import bean.Chambre;
import bean.Reservation;
import bean.ReservationStatus;

import java.util.List;

public class StatisticService {
    private ReservationService reservationService;
    private ChambreService chambreService;

    public StatisticService() {
        this.reservationService = new ReservationService();
        this.chambreService = new ChambreService();
    }

    public void reservationCancelledCount() {
        List<Reservation> reservations = reservationService.getAllReservations();
        long cancelledReservations = reservations.stream()
                .filter(reservation -> reservation.getStatus() == ReservationStatus.CANCELLED)
                .count();

        System.out.println("Nombre de réservations annulées : " + cancelledReservations);
    }

    public void reservationReservedCount() {
        List<Reservation> reservations = reservationService.getAllReservations();
        long cancelledReservations = reservations.stream()
                .filter(reservation -> reservation.getStatus() == ReservationStatus.RESERVED)
                .count();

        System.out.println("Nombre de réservations confirmées : " + cancelledReservations);
    }

    public void calculTauxOccupation(){
        List<Reservation> reservations = reservationService.getAllReservations();
        List<Chambre> chambres=chambreService.getAllChambre();
        long totalRooms=chambres.size();
        long ReservedRooms= reservations.stream().filter(reservation -> reservation.getStatus()==ReservationStatus.RESERVED)
                .map(Reservation::getChambre)
                .distinct()
                .count();
        System.out.println("Total rooms : " + totalRooms);
        System.out.println("Reserved rooms : " + ReservedRooms);
        double tauxOccupation = ((double) ReservedRooms / totalRooms) * 100;
        System.out.println("Taux d'occupations : " + tauxOccupation);
    }
}
