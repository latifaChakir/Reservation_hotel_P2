package repository.dao;

import bean.Chambre;
import java.sql.SQLException;
import java.util.List;

public abstract class ChambreDao {
    public abstract List<Chambre> getAllChambres();
    public abstract Chambre getChambreById(int chambreId);
    public abstract void saveChambre(Chambre chambre);
    public abstract void updateChambre(Chambre chambre);
    public abstract void deleteChambre(int chambreId);
}
