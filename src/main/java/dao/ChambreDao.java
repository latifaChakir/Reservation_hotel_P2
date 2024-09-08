package dao;

import bean.Chambre;
import connection.ConnectionConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChambreDao {
    public List<Chambre> getAllChambres() throws SQLException {
        List<Chambre> chambres = new ArrayList<>();
        String query = "SELECT * FROM chambre";

        try (Connection conn = ConnectionConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int numero = rs.getInt("numero");
                String type = rs.getString("type");
                boolean disponible = rs.getBoolean("disponible");
                chambres.add(new Chambre(numero, type, disponible));
            }
        }
        return chambres;
    }

    public Chambre getChambreById(int chambreId) throws SQLException {
        Chambre chambre = null;
        String query = "SELECT * FROM chambre WHERE id = ?";

        try (Connection conn = ConnectionConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, chambreId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int numero = rs.getInt("numero");
                    String type = rs.getString("type");
                    boolean disponible = rs.getBoolean("disponible");

                    chambre = new Chambre(numero, type, disponible);
                }
            }
        }

        return chambre;
    }
}
