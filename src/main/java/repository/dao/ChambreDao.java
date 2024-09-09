package repository.dao;

import bean.Chambre;
import java.sql.SQLException;
import java.util.List;

public abstract class ChambreDao {
    public abstract List<Chambre> getAllChambres() throws SQLException;
    public abstract Chambre getChambreById(int chambreId) throws SQLException;
    public abstract void saveChambre(Chambre chambre) throws SQLException;
    public abstract void updateChambre(Chambre chambre) throws SQLException;
    public abstract void deleteChambre(int chambreId) throws SQLException;
}
