package service;


import bean.Chambre;
import repository.impl.ChambreDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class ChambreService {

    private ChambreDaoImpl chambreDao;

    public ChambreService() throws SQLException {
        this.chambreDao = new ChambreDaoImpl();
    }

    public List<Chambre> getAllChambres() throws SQLException {
        return chambreDao.getAllChambres();
    }

    public Chambre getChambreById(int id) throws SQLException {
        return chambreDao.getChambreById(id);
    }

    public void saveChambre(Chambre chambre) throws SQLException {
        chambreDao.saveChambre(chambre);
    }

    public void updateChambre(Chambre chambre) throws SQLException {
        chambreDao.updateChambre(chambre);
    }

    public void deleteChambre(int id) throws SQLException {
        chambreDao.deleteChambre(id);
    }
}
