package dao;

import bean.Chambre;
import bean.Client;
import bean.Reservation;
import connection.ConnectionConfig;

import java.sql.*;
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
                int clientId = rs.getInt("client_id");
                int chambreId = rs.getInt("chambre_id");
                Date dateDebut = rs.getDate("date_debut");
                Date dateFin = rs.getDate("date_fin");

                Client client = new ClientDao().getClientById(clientId);
                Chambre chambre = new ChambreDao().getChambreById(chambreId);

                // Création et ajout de la réservation à la liste
                reservations.add(new Reservation(id, client, chambre, dateDebut.toLocalDate(), dateFin.toLocalDate()));
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
