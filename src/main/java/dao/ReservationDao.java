package dao;

import bean.Chambre;
import bean.Client;
import bean.Reservation;
import connection.ConnectionConfig;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {

    private Connection conn;

    public ReservationDao() throws SQLException {
        this.conn = ConnectionConfig.getInstance().getConnection();
    }

    public List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int clientId = rs.getInt("id_client");
                int chambreId = rs.getInt("id_chambre");
                Date dateDebut = rs.getDate("date_debut");
                Date dateFin = rs.getDate("date_fin");

                Client client = new ClientDao().getClientById(clientId);
                Chambre chambre = new ChambreDao().getChambreById(chambreId);

                reservations.add(new Reservation(client, chambre, dateDebut.toLocalDate(), dateFin.toLocalDate()));
            }
        }

        return reservations;
    }
    public void ajouterReservation(Reservation reservation) throws SQLException {
        if (reservation.getClient() == null || reservation.getClient().getId() == 0) {
            throw new SQLException("Client non valide pour la réservation.");
        }

        String query = "INSERT INTO reservations (id_client, id_chambre, date_debut, date_fin) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, reservation.getClient().getId());
            pstmt.setInt(2, reservation.getChambre().getNumero());
            pstmt.setDate(3, Date.valueOf(reservation.getDateDebut()));
            pstmt.setDate(4, Date.valueOf(reservation.getDateFin()));

            pstmt.executeUpdate();
        }
    }

    public List<Reservation> getReservationsByChambre(int chambreId) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations WHERE id_chambre = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chambreId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int reservationId = rs.getInt("id");
                    int clientId = rs.getInt("id_client");
                    LocalDate debut = rs.getDate("date_debut").toLocalDate();
                    LocalDate fin = rs.getDate("date_fin").toLocalDate();

                    Reservation reservation = new Reservation( null, null, debut, fin);
                    reservations.add(reservation);
                }
            }
        }

        return reservations;
    }

    public void closeConnection() {
        if (this.conn != null) {
            try {
                this.conn.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
