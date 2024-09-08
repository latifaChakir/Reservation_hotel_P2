package service;

import bean.Chambre;
import bean.Client;
import bean.Reservation;
import dao.ChambreDao;
import dao.ReservationDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class HotelService {

    private ChambreDao chambreDao;
    private ReservationDao reservationDao;

    public HotelService() throws SQLException {
        this.chambreDao = new ChambreDao();
        this.reservationDao = new ReservationDao();
    }

    public boolean creerReservation(Client client, int numeroChambre, LocalDate debut, LocalDate fin) throws SQLException {
        Chambre chambre = chambreDao.getChambreById(numeroChambre);
        if (chambre != null) {
            Reservation reservation = new Reservation(client, chambre, debut, fin);
            reservationDao.ajouterReservation(reservation);
            return true;
        }
        return false;
    }

    public List<Chambre> getAllChambres() throws SQLException {
        return chambreDao.getAllChambres();
    }

    public void annulerReservation(int reservationId) throws SQLException {
    }

    public void modifierReservation(int reservationId, Client client, LocalDate debut, LocalDate fin, int numeroChambre) throws SQLException {
    }

}
