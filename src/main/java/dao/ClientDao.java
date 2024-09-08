package dao;

import bean.Client;
import connection.ConnectionConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    private Connection conn;

    public ClientDao() throws SQLException {
        this.conn = ConnectionConfig.getInstance().getConnection();
    }

    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";

        try (Statement stmt = conn.createStatement();
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

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
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
