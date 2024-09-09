package repository.impl;

import bean.Client;
import connection.ConnectionConfig;
import repository.dao.ClientDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl extends ClientDao {

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";

        try (Connection conn = ConnectionConfig.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                String phone = rs.getString("phone");

                clients.add(new Client(name, age, address, phone));
            }
        } catch (SQLException sqlException) {
            System.out.println("Error fetching clients: " + sqlException.getMessage());
        }

        return clients;
    }

    @Override
    public Client getClientById(int clientId) {
        Client client = null;
        String query = "SELECT * FROM clients WHERE id = ?";

        try (Connection conn = ConnectionConfig.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, clientId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");

                    client = new Client(name, age, address, phone); // Assurez-vous d'avoir l'ID dans le constructeur
                }
            }
        } catch (SQLException sqlException) {
            System.out.println("Error fetching client by ID: " + sqlException.getMessage());
        }

        return client;
    }

    @Override
    public Client saveClient(Client client) {
        String query = "INSERT INTO clients (name, age, address, phone) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionConfig.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, client.getName());
            pstmt.setInt(2, client.getAge());
            pstmt.setString(3, client.getAddress());
            pstmt.setString(4, client.getPhone());
            pstmt.executeUpdate();

            System.out.println("Client saved successfully!");
        } catch (SQLException sqlException) {
            System.out.println("Error saving client: " + sqlException.getMessage());
        }

        return client;
    }

    @Override
    public void updateClient(Client client) {
        String query = "UPDATE clients SET name = ?, age = ?, address = ?, phone = ? WHERE id = ?";

        try (Connection conn = ConnectionConfig.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, client.getName());
            pstmt.setInt(2, client.getAge());
            pstmt.setString(3, client.getAddress());
            pstmt.setString(4, client.getPhone());
            pstmt.setInt(5, client.getId());
            pstmt.executeUpdate();

            System.out.println("Client updated successfully!");
        } catch (SQLException sqlException) {
            System.out.println("Error updating client: " + sqlException.getMessage());
        }
    }

    @Override
    public void deleteClient(int clientId) {
        String query = "DELETE FROM clients WHERE id = ?";

        try (Connection conn = ConnectionConfig.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, clientId);
            pstmt.executeUpdate();

            System.out.println("Client deleted successfully!");
        } catch (SQLException sqlException) {
            System.out.println("Error deleting client: " + sqlException.getMessage());
        }
    }
}
