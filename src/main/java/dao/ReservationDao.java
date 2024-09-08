package dao;
import bean.Chambre;
import bean.Client;
import bean.Reservation;
import connection.ConnectionConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ReservationDao {

    public List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations";

        try (Connection conn = ConnectionConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int clientId = rs.getInt("client_id");
                int chambreId = rs.getInt("chambre_id");
                Date dateDebut = rs.getDate("date_debut");
                Date dateFin = rs.getDate("date_fin");
                Client client = new ClientDao().getClientById(clientId);
                Chambre chambre = new ChambreDao().getChambreById(chambreId);
                reservations.add(new Reservation(id, client, chambre, dateDebut.toLocalDate(), dateFin.toLocalDate()));
            }
        }
        return reservations;
    }

}
