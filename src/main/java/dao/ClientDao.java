package dao;
import bean.Client;
import connection.ConnectionConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ClientDao {
    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";

        try (Connection conn = ConnectionConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                String phone = rs.getString("phone");

                clients.add(new Client(nom, age, address, phone));
            }
        }
        return clients;
    }

    public Client getClientById(int clientId) throws SQLException {
        Client client = null;
        String query = "SELECT * FROM clients WHERE id = ?";

        try (Connection conn = ConnectionConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, clientId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("nom");
                    int age = rs.getInt("age");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");

                    client = new Client(nom, age, address, phone);
                }
            }
        }

        return client;
    }
}
