package repository.impl;

import bean.Client;
import connection.ConnectionConfig;
import repository.dao.ClientDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl extends ClientDao {

    private Connection conn;

    public ClientDaoImpl() throws SQLException {
        this.conn = ConnectionConfig.getInstance().getConnection();
    }

    @Override
    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                String phone = rs.getString("phone");

                clients.add(new Client(id, name, age, address, phone));
            }
        }

        return clients;
    }

    @Override
    public Client getClientById(int clientId) throws SQLException {
        Client client = null;
        String query = "SELECT * FROM clients WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, clientId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");

                    client = new Client(clientId, name, age, address, phone);
                }
            }
        }

        return client;
    }

    @Override
    public void saveClient(Client client) throws SQLException {
        String query = "INSERT INTO clients (name, age, address, phone) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, client.getName());
            pstmt.setInt(2, client.getAge());
            pstmt.setString(3, client.getAddress());
            pstmt.setString(4, client.getPhone());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateClient(Client client) throws SQLException {
        String query = "UPDATE clients SET name = ?, age = ?, address = ?, phone = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, client.getName());
            pstmt.setInt(2, client.getAge());
            pstmt.setString(3, client.getAddress());
            pstmt.setString(4, client.getPhone());
            pstmt.setInt(5, client.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteClient(int clientId) throws SQLException {
        String query = "DELETE FROM clients WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, clientId);
            pstmt.executeUpdate();
        }
    }


}
