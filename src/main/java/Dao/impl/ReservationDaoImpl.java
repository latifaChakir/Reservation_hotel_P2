package Dao.impl;

import bean.Chambre;
import bean.Client;
import bean.Reservation;
import enums.ReservationStatus;
import connection.ConnectionConfig;
import Dao.dao.ReservationDao;
import service.PrixDynamiqueService;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDaoImpl extends ReservationDao {
    private ClientDaoImpl clientDaoImpl;
    private ChambreDaoImpl chambreDaoImpl;
    private PrixDynamiqueService prixDynamiqueService;

    public ReservationDaoImpl() {
        clientDaoImpl = new ClientDaoImpl();
        chambreDaoImpl = new ChambreDaoImpl();
        prixDynamiqueService = new PrixDynamiqueService();
    }
    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT r.*, rs.status FROM reservations r " +
                "JOIN reservations_status rs ON r.status_id = rs.id";

        try (Connection conn = ConnectionConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation();
                int reservationId = rs.getInt("id");
                int clientId = rs.getInt("id_client");
                int chambreId = rs.getInt("id_chambre");
                String statusName = rs.getString("status");

                ReservationStatus status = ReservationStatus.valueOf(statusName.toUpperCase());

                reservation.setId(reservationId);
                reservation.setDateDebut(rs.getDate("date_debut").toLocalDate());
                reservation.setDateFin(rs.getDate("date_fin").toLocalDate());
                reservation.setStatus(status);

                Chambre chambre = chambreDaoImpl.getChambreById(chambreId);
                Client client = clientDaoImpl.getClientById(clientId);
                reservation.setClient(client);
                reservation.setChambre(chambre);

                reservations.add(reservation);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status value from database", e);
        }

        return reservations;
    }

    @Override
    public Reservation getReservationById(int reservationId) {
        Reservation reservation = null;
        String sql = "select * from reservations where id=?";
        try (Connection conn=ConnectionConfig.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement(sql)) {
            ps.setInt(1, reservationId);
            try(ResultSet rs=ps.executeQuery();) {
                if(rs.next()) {
                    int clientID = rs.getInt("id_client");
                    int roomID = rs.getInt("id_chambre");
                    reservation.setDateDebut(rs.getDate("date_debut").toLocalDate());
                    reservation.setDateFin(rs.getDate("date_fin").toLocalDate());
                    Chambre chambre=chambreDaoImpl.getChambreById(roomID);
                    Client client=clientDaoImpl.getClientById(clientID);
                    reservation.setChambre(chambre);
                    reservation.setClient(client);
                }

            }catch (SQLException e) {
                e.printStackTrace();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservation;
    }
    private boolean isRoomAvailable(long roomId, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT COUNT(*) FROM reservations WHERE id_chambre = ? AND " +
                "(date_debut < ? AND date_fin > ?)";

        try (Connection conn = ConnectionConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, roomId);
            ps.setDate(2, Date.valueOf(endDate));
            ps.setDate(3, Date.valueOf(startDate));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0; // If count is 0, room is available
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking room availability", e);
        }

        return false;
    }


    @Override
    public Reservation saveReservation(Reservation reservation) {
        if (!isRoomAvailable(reservation.getChambre().getId(), reservation.getDateDebut(), reservation.getDateFin())) {
            throw new RuntimeException("La chambre n'est pas disponible pour les dates sélectionnées.");
        }

        String sql = "INSERT INTO reservations(id_client, id_chambre, date_debut, date_fin, status_id, total_price) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;

        try {
            conn = ConnectionConfig.getInstance().getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Calculate total price
            BigDecimal totalPrice = prixDynamiqueService.calculeTotalPrice(reservation);
            reservation.setTotal_price(totalPrice);

            // Set query parameters
            ps.setLong(1, reservation.getClient().getId());
            ps.setLong(2, reservation.getChambre().getId());
            ps.setDate(3, Date.valueOf(reservation.getDateDebut()));
            ps.setDate(4, Date.valueOf(reservation.getDateFin()));
            ps.setInt(5, 1); // Default reservation status (1 = new reservation)
            ps.setBigDecimal(6, totalPrice);
            System.out.println("Reservation added successfully, total price: " + totalPrice);

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted == 0) {
                throw new RuntimeException("Failed to insert reservation");
            }

            // Retrieve the generated ID
            generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                reservation.setId(generatedKeys.getInt(1));
            } else {
                throw new RuntimeException("Failed to retrieve generated ID for reservation");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while adding reservation", e);
        } finally {
            // Close resources in reverse order
            if (generatedKeys != null) try { generatedKeys.close(); } catch (SQLException e) { /* ignore */ }
            if (ps != null) try { ps.close(); } catch (SQLException e) { /* ignore */ }
            if (conn != null) ConnectionConfig.closeConnection(conn);
        }

        return reservation;
    }

    @Override
    public void updateReservation(Reservation reservation) {
        String sql="update reservations set id_client=?, id_chambre=?, date_debut=?, date_fin=? where id=?";
        try(Connection conn=ConnectionConfig.getInstance().getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)
        ){
            Date dateDebut=Date.valueOf(reservation.getDateDebut());
            Date dateFin=Date.valueOf(reservation.getDateFin());
            ps.setLong(1,reservation.getClient().getId());
            ps.setLong(2,reservation.getChambre().getId());
            ps.setDate(3,dateDebut);
            ps.setDate(4,dateFin);
            ps.setInt(5,reservation.getId());
            ps.executeUpdate();
            System.out.println("Rservation updated successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteReservation(int reservationId) {
        String sql="delete from reservations where id=?";
        try(Connection conn=ConnectionConfig.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,reservationId);
            ps.executeUpdate();
            System.out.println("Reservation successfully deleted");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void cancelReservation(int reservationId) {
        String sql="UPDATE reservations set status_id=2 where id=?";
        try(Connection conn=ConnectionConfig.getInstance().getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,reservationId);
            ps.executeUpdate();
            System.out.println("status updated successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
