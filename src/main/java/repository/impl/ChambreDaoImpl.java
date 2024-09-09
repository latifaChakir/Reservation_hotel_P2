package repository.impl;
import bean.Chambre;
import bean.RoomType;
import connection.ConnectionConfig;
import repository.dao.ChambreDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChambreDaoImpl extends ChambreDao {

    private Connection conn;

    public ChambreDaoImpl() throws SQLException {
        this.conn = ConnectionConfig.getInstance().getConnection();
    }

    @Override
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
                }                Chambre chambre = new Chambre(numero, type, disponible);
                chambres.add(chambre);
            }
        }

        return chambres;
    }

    @Override
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

    @Override
    public void saveChambre(Chambre chambre) throws SQLException {
        String query = "INSERT INTO chambre (numero, type, isdisponible) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chambre.getNumero());
            pstmt.setString(2, chambre.getType().toString());
            pstmt.setBoolean(3, chambre.isDisponible());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void updateChambre(Chambre chambre) throws SQLException {
        String query = "UPDATE chambre SET numero = ?, type = ?, isdisponible = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chambre.getNumero());
            pstmt.setString(2, chambre.getType().toString());
            pstmt.setBoolean(3, chambre.isDisponible());
            pstmt.setInt(4, chambre.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteChambre(int chambreId) throws SQLException {
        String query = "DELETE FROM chambre WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, chambreId);
            pstmt.executeUpdate();
        }
    }
}
