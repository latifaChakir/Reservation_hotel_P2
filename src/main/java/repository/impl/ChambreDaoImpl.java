package repository.impl;

import bean.Chambre;
import bean.RoomType;
import connection.ConnectionConfig;
import repository.dao.ChambreDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChambreDaoImpl extends ChambreDao {

    @Override
    public List<Chambre> getAllChambres() {
        List<Chambre> chambres = new ArrayList<>();
        String query = "SELECT * FROM chambre";

        try (Connection conn = ConnectionConfig.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int numero = rs.getInt("numero");
                String typeStr = rs.getString("type");
                boolean disponible = rs.getBoolean("isdisponible");

                RoomType type = null;
                if (typeStr != null) {
                    type = RoomType.valueOf(typeStr.toUpperCase());
                }
                Chambre chambre = new Chambre(numero, type, disponible);
                chambres.add(chambre);
            }
        } catch (SQLException sqlException) {
            System.out.println("Error fetching chambres: " + sqlException.getMessage());
        }

        return chambres;
    }

    @Override
    public Chambre getChambreById(int chambreId) {
        Chambre chambre = null;
        String query = "SELECT * FROM chambre WHERE id = ?";

        try (Connection conn = ConnectionConfig.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chambreId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int numero = rs.getInt("numero");
                    String typeStr = rs.getString("type");
                    boolean disponible = rs.getBoolean("isdisponible");

                    RoomType type = null;
                    if (typeStr != null) {
                        type = RoomType.valueOf(typeStr.toUpperCase());
                    }
                    chambre = new Chambre(numero, type, disponible);
                }
            }
        } catch (SQLException sqlException) {
            System.out.println("Error fetching chambre by ID: " + sqlException.getMessage());
        }

        return chambre;
    }

    @Override
    public void saveChambre(Chambre chambre) {
        String query = "INSERT INTO chambre (numero, type, isdisponible) VALUES (?, ?::chambre_type, ?)";

        try (Connection conn = ConnectionConfig.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chambre.getNumero());
            pstmt.setString(2, chambre.getType().toString());
            pstmt.setBoolean(3, chambre.isDisponible());
            pstmt.executeUpdate();

            System.out.println("Chambre saved successfully!");
        } catch (SQLException sqlException) {
            System.out.println("Error saving chambre: " + sqlException.getMessage());
        }
    }

    @Override
    public void updateChambre(Chambre chambre) {
        String query = "UPDATE chambre SET numero = ?, type = ?::chambre_type, isdisponible = ? WHERE id = ?";

        try (Connection conn = ConnectionConfig.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chambre.getNumero());
            pstmt.setString(2, chambre.getType().toString());
            pstmt.setBoolean(3, chambre.isDisponible());
            pstmt.setInt(4, chambre.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Chambre updated successfully!");
            } else {
                System.out.println("No chambre found with ID: " + chambre.getId());
            }
        } catch (SQLException sqlException) {
            System.out.println("Error updating chambre: " + sqlException.getMessage());
        }
    }

    @Override
    public void deleteChambre(int chambreId) {
        String query = "DELETE FROM chambre WHERE id = ?";

        try (Connection conn = ConnectionConfig.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chambreId);
            pstmt.executeUpdate();

            System.out.println("Chambre deleted successfully!");
        } catch (SQLException sqlException) {
            System.out.println("Error deleting chambre: " + sqlException.getMessage());
        }
    }
}
