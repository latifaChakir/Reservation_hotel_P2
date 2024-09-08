package dao;

import bean.Chambre;
import bean.RoomType;
import connection.ConnectionConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChambreDao {

    private Connection conn;

    public ChambreDao() throws SQLException {
        this.conn = ConnectionConfig.getInstance().getConnection();
    }

    public List<Chambre> getAllChambres() throws SQLException {
        List<Chambre> chambres = new ArrayList<>();
        String query = "SELECT * FROM chambre";

        try (Statement stmt = conn.createStatement();
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
        }

        return chambres;
    }

    public Chambre getChambreById(int chambreId) throws SQLException {
        Chambre chambre = null;
        String query = "SELECT * FROM chambre WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
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
        }

        return chambre;
    }

}
